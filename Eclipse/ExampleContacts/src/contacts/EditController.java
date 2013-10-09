package contacts;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.event.ForwardEvent;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zkplus.databind.AnnotateDataBinder;
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
 * Add/edit contact controller
 * 
 * See edit.zul
 */
public class EditController extends GenericForwardComposer<Window> {

	public Contact getContact() {
		return contact;
	}

	@Override
	public void doAfterCompose(Window win) throws Exception {
		super.doAfterCompose(win);
		binder = new AnnotateDataBinder(self);
		self.setAttribute("binder", binder);
	}

	public void onCreate$form(ForwardEvent event) {
		if (self.getAttribute("origin") == null) // add
		{
			self.setTitle("Add Contact...");
		} else // edit
		{
			self.setTitle("Edit Contact...");
			// cloned to avoid apply changes in case of
			// 'cancel' pressed or constraints not passed
			contact = ((Contact) self.getAttribute("origin")).getCopy();
			name.setDisabled(true);
			address.setFocus(true);
		}

		binder.loadAll();
	}

	/*
	 * Process OK button
	 */
	public void onClick$ok() {
		binder.saveComponent(input);

		// add
		if (self.getAttribute("origin") == null) {
			self.setAttribute("Contact", contact);

			if (ContactsContainer.INSTANCE.exist(contact)) {
				Clients.showNotification("Duplicated contact not allowed.",
						"warning", name, "end_center", -1);
				return;
			}

			ContactsContainer.INSTANCE.add(contact);
		}
		// edit
		else {
			ContactsContainer.INSTANCE.update(contact);
		}

		Events.postEvent(new Event("onOK", self, null));
		self.detach();
	}

	/*
	 * process Cancel button
	 */
	public void onClick$cancel() {
		self.detach();
	}
	
	private static final long serialVersionUID = 4364054586343674357L;
	private Contact contact = new Contact();
	private Textbox name;
	private Textbox address;
	private Component input;
	private AnnotateDataBinder binder;

}
