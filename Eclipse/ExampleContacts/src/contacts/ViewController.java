package contacts;

import java.util.ArrayList;
import java.util.Collection;

import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.ForwardEvent;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zkplus.databind.AnnotateDataBinder;
import org.zkoss.zul.Button;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

/**
 * This file is part of ExampleContacts demo project.
 * 
 *  ExampleContacts is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *  
 *  ExampleContacts is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *  
 *  You should have received a copy of the GNU General Public License
 *  along with ExampleContacts.  If not, see <http://www.gnu.org/licenses/>.
 *  
 *  @author Enrico Tedeschini
 *  
 * View contacts controller
 * 
 * See view.zul
 */
public class ViewController extends GenericForwardComposer<Window> {

	public Collection<Contact> getContacts() {
		final String text = search.getText().toLowerCase().trim();

		Collection<Contact> list = ContactsContainer.INSTANCE.getContacts();

		if (text.length() == 0)
			return list;

		Collection<Contact> l = filter(list, new Predicate<Contact>() {
			public boolean evaluate(Contact obj) {
				if (obj.name.toLowerCase().contains(text))
					return true;
				return false;
			}
		});

		return l;
	}

	@Override
	public void doAfterCompose(Window win) throws Exception {
		super.doAfterCompose(win);
		binder = new AnnotateDataBinder(self);
		self.setAttribute("binder", binder);
	}

	/**
	 * Create view
	 */
	public void onCreate$view(ForwardEvent event) {
		binder.loadAll();
	}

	/**
	 * Update buttons status after rendering
	 */
	public void onAfterRender$contactsList(Event event) {
		updateToolbarStatus();
	}

	/**
	 * Selected item(s) on the list
	 */
	public void onSelect$contactsList() {
		updateToolbarStatus();
	}

	/**
	 * Add button action
	 */
	public void onClick$add() {
		Window win = (Window) Executions.createComponents("edit.zul", null,
				null);
		win.doModal();
		win.addEventListener("onOK", new EventListener<Event>() {

			@Override
			public void onEvent(Event event) throws Exception {
				onClick$refresh();
			}
		});
	}

	/**
	 * Edit button action
	 */
	public void onClick$edit() {
		Listitem item = contactsList.getSelectedItem();
		Contact bean = (Contact) item.getValue();
		Window win = (Window) Executions.createComponents("edit.zul", null,
				null);
		win.setAttribute("origin", bean);
		win.doModal();

		win.addEventListener("onOK", new EventListener<Event>() {

			@Override
			public void onEvent(Event event) throws Exception {
				Listitem item = contactsList.getSelectedItem();
				binder.loadComponent(item); // refresh component on client side
				updateToolbarStatus();
			}
		});
	}

	/**
	 * Remove button action
	 */
	public void onClick$remove() {
		Messagebox.show("Are you sure you want to remove selected Contact(s)?",
				"Contacts", Messagebox.OK | Messagebox.CANCEL,
				Messagebox.QUESTION,

				new EventListener<Event>() {
					@Override
					public void onEvent(Event event) throws Exception {
						if (event.getName().equals("onOK")) {
							for (Listitem item : contactsList
									.getSelectedItems()) {
								Contact bean = (Contact) item.getValue();
								ContactsContainer.INSTANCE.remove(bean);
							}

							onClick$refresh();
						}
					}
				});
	}

	/**
	 * Refresh button action
	 */
	public void onClick$refresh() {
		binder.loadComponent(contactsList);
	}

	/**
	 * [Enter] search textbox action
	 */
	public void onOK$search() {
		onClick$refresh();
	}

	// ////////////////////////////////////////////////////////////////////////////////////////
	// internal functions

	private void updateToolbarStatus() {

		int count = contactsList.getSelectedCount();
		if (count > 1) {
			edit.setDisabled(true);
			remove.setDisabled(false);
		} else if (count == 1) {
			edit.setDisabled(false);
			remove.setDisabled(false);
		} else {
			edit.setDisabled(true);
			remove.setDisabled(true);
		}
	}

	private interface Predicate<T> {
		public boolean evaluate(T type);
	}

	private <T> Collection<T> filter(final Collection<T> target,
			Predicate<T> predicate) {
		Collection<T> c = new ArrayList<T>();
		for (T t : target) {
			if (predicate.evaluate(t)) {
				c.add(t);
			}
		}
		return c;
	}
	
	private static final long serialVersionUID = -7213061567670343159L;
	private AnnotateDataBinder binder;
	private Listbox contactsList;
	private Button edit;
	private Button remove;
	private Textbox search;
}
