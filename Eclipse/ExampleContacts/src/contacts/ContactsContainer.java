package contacts;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

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
 * Singleton 
 * Contacts container
 */
public enum ContactsContainer
{
	INSTANCE;
	
	private Map<String, Contact> _container = new LinkedHashMap<String, Contact>();

	ContactsContainer() {
		loadContacts();
	}
	
	public Collection<Contact> getContacts() {
		return new ArrayList<Contact>(_container.values());
	}

	/** 
	 * Check if already exist same contact
	 */
	public boolean exist(Contact bean) {
		return _container.containsKey(bean.getKey());
	}

	/** 
	 * Add contact 
	 * @return true if succeeded
	 */
	public boolean add(Contact bean) {
		if (bean == null)
			return false;

		if (_container.get(bean.getKey()) != null)
			return false;

		_container.put(bean.getKey(), bean);
		return true;
	}

	/** 
	 * Update contact 
	 * @return true if succeeded
	 */
	public boolean update(Contact modified)
    {
		Contact origin = _container.get(modified.getKey());
		
		if (origin == null)
			return false;
		
		origin.updateBy(modified);
		return true;
    }

	/** 
	 * Remove contact 
	 * @return true if succeeded
	 */
	public boolean remove(Contact bean)
	{
		if (bean == null)
			return false;
		
		_container.remove(bean.getKey());
		return true;
	}
	
	/** 
	 * load list of contacts
	 * 
	 * All these name, address are randomly generated. See http://www.generatedata.com/
	 * They are completely fake 
	 */
	private void loadContacts() {
		
	 String[] data = new String[] {
		 "Lavinia Mcmillan", "8609 Mollis Rd.",  "United Kingdom", "+44 931 2114-5937",  "imperdiet.erat.nonummy@consequatdolor.net",
		 "Camille Bauer", "P.O. Box 205, 5585 Nec Rd.",  "United Kingdom", "+44 711 2195-0361",  "congue.In@adipiscingelit.ca",
		 "Ursa Rojas", "P.O. Box 596, 4626 Eleifend, Ave",  "United Kingdom", "+44 617 8230-9510",  "dolor.Donec@diamat.org",
		 "Daria Ramos", "945-6301 Pede. Avenue",  "United Kingdom", "+44 709 8641-2590",  "ligula.Aenean.gravida@congue.net",
		 "Christine Shannon", "149-7993 Curabitur Street",  "United Kingdom", "+44 876 2624-4343",  "eros.Proin.ultrices@ataugue.org",
		 "Jermaine Conley", "P.O. Box 988, 6275 Lorem, Av.",  "United Kingdom", "+44 807 3159-9360",  "sem.ut.dolor@Suspendisseeleifend.com",
		 "Tanek Payne", "P.O. Box 473, 2077 Dolor Ave",  "United Kingdom", "+44 644 8412-2105",  "aliquet@ligulaAenean.co.uk",
//		 "Benedict Ewing", "3393 Gravida. St.",  "United Kingdom", "+44 605 4296-9504",  "ornare.sagittis@aliquetlobortis.net",
//		 "Nicole Reid", "763-2127 Torquent Avenue",  "United Kingdom", "+44 842 8251-8662",  "metus.urna.convallis@tellussemmollis.com",
//		 "Wayne Cunningham", "Ap #352-1899 Id, Road",  "United Kingdom", "+44 144 4167-0198",  "lectus.rutrum@molestie.edu",
//		 "Myles Lowe", "765-4842 Feugiat Street",  "United Kingdom", "+44 908 7301-3467",  "taciti.sociosqu@venenatislacus.org",
//		 "Ivor Lawrence", "Ap #121-475 Tristique Avenue",  "United Kingdom", "+44 695 1704-6023",  "egestas.rhoncus@sitamet.net",
//		 "Camden Mays", "P.O. Box 990, 8127 Odio Road",  "United Kingdom", "+44 554 1652-1581",  "elit@ipsumnunc.co.uk",
//		 "Robin Fleming", "735 Arcu. St.",  "United Kingdom", "+44 960 4702-2577",  "Nulla.aliquet@ipsum.ca",
//		 "Priscilla Patel", "Ap #396-8988 Sed Avenue",  "United Kingdom", "+44 216 2079-0304",  "In@tristiqueac.net",
//		 "Illiana Bryant", "294-3953 Auctor Av.",  "United Kingdom", "+44 911 4408-6247",  "cubilia@molestieSedid.ca",
//		 "Philip Norris", "171-5229 Nunc Av.",  "United Kingdom", "+44 285 6096-9084",  "iaculis.lacus@enim.edu",
//		 "Ori Webb", "Ap #590-5765 Aliquam Avenue",  "United Kingdom", "+44 222 5382-5681",  "metus.facilisis.lorem@dolortempusnon.co.uk",
//		 "Maia Barrera", "601-5219 Tincidunt, Rd.",  "United Kingdom", "+44 589 2356-5890",  "ac@Sedcongue.org",
//		 "Chantale Sparks", "Ap #687-8035 Sapien, Avenue",  "United Kingdom", "+44 727 9729-2094",  "Donec.tempor@ullamcorper.com",
//		 "Ahmed Buck", "330-863 Nibh Ave",  "United Kingdom", "+44 206 8740-4684",  "leo.elementum@atiaculis.com",
//		 "Fuller Bright", "P.O. Box 730, 1497 Nunc Av.",  "United Kingdom", "+44 869 3909-0158",  "mauris.Integer.sem@ametmassaQuisque.ca",
//		 "Hector Lawrence", "7429 Tellus. Rd.",  "United Kingdom", "+44 355 7179-8407",  "sem.vitae@dictumaugue.ca",
//		 "Steven Rogers", "9911 Vel St.",  "United Kingdom", "+44 433 8925-4519",  "inceptos@idantedictum.edu",
//		 "Brian Cruz", "395-1937 At, Rd.",  "United Kingdom", "+44 294 4741-0748",  "semper.tellus.id@vitaemauris.com",
//		 "Lilah Chavez", "7429 Cursus Av.",  "United Kingdom", "+44 999 6328-2476",  "amet.dapibus.id@facilisis.ca",
//		 "Sara Macias", "924-7842 Enim Rd.",  "United Kingdom", "+44 591 4894-8834",  "Ut@orci.edu",
//		 "Akeem Barnett", "P.O. Box 978, 1232 Ut Rd.",  "United Kingdom", "+44 554 8424-0263",  "non@semegestasblandit.org",
//		 "Callie Fleming", "Ap #147-7970 Sociis Ave",  "United Kingdom", "+44 170 2610-2750",  "neque@arcuiaculis.edu",
//		 "Chancellor Ortega", "995-2034 Massa. Ave",  "United Kingdom", "+44 383 8813-9841",  "dis.parturient@fringilla.org",
//		 "Rooney Stewart", "102-7605 Ut Ave",  "United Kingdom", "+44 783 2858-1329",  "Integer@Proinnisl.com",
//		 "Rosalyn Hartman", "Ap #746-8911 Nunc Rd.",  "United Kingdom", "+44 651 1685-2794",  "ut.nulla@eu.net",
//		 "Kristen Scott", "P.O. Box 387, 2996 Cursus. Avenue",  "United Kingdom", "+44 724 4937-5995",  "placerat.eget@malesuada.ca",
//		 "Alan Gibson", "Ap #130-2959 Lobortis Rd.",  "United Kingdom", "+44 831 1640-3657",  "enim.mi@Namconsequat.co.uk",
//		 "Irma Montgomery", "Ap #321-7669 Proin St.",  "United Kingdom", "+44 692 3054-8869",  "leo.elementum@aarcuSed.co.uk",
//		 "Judith Patton", "P.O. Box 609, 2309 Sit Road",  "United Kingdom", "+44 269 9866-4696",  "vestibulum.neque@ac.com",
//		 "Rudyard Herrera", "856-8111 Suspendisse Avenue",  "United Kingdom", "+44 522 8844-0139",  "accumsan.sed.facilisis@laoreetlectus.net",
//		 "Kylee Burke", "845-3087 Parturient Avenue",  "United Kingdom", "+44 310 6085-5173",  "libero.at.auctor@aliquetnecimperdiet.org",
//		 "Damian Walker", "Ap #832-9832 Porttitor Street",  "United Kingdom", "+44 931 2143-3630",  "posuere.at@loremsemper.org",
//		 "Fredericka Marquez", "375-386 Nascetur Rd.",  "United Kingdom", "+44 349 8299-1203",  "Etiam.gravida@nullavulputatedui.edu",
//		 "Amena Richards", "972-6723 Convallis Street",  "United Kingdom", "+44 592 9325-5753",  "nunc.risus.varius@nec.ca",
//		 "Ifeoma Hurley", "P.O. Box 803, 996 Ut Avenue",  "United Kingdom", "+44 584 8814-3173",  "dolor.Quisque@laoreetposuereenim.net",
//		 "Hilary Green", "Ap #774-5024 Aenean Av.",  "United Kingdom", "+44 250 4315-8007",  "sem.ut.cursus@turpis.com",
//		 "Violet Kirk", "760-6991 In Avenue",  "United Kingdom", "+44 136 8118-3696",  "metus.In.nec@mi.edu",
//		 "Chloe Levine", "Ap #223-6361 Posuere Road",  "United Kingdom", "+44 636 1138-1649",  "tellus.non.magna@mollisnoncursus.com",
//		 "Roanna Richmond", "217 Nulla Avenue",  "United Kingdom", "+44 259 5201-9755",  "amet.nulla.Donec@cursusvestibulumMauris.net",
//		 "Lilah Alvarado", "Ap #686-5028 Elit. Road",  "United Kingdom", "+44 520 4203-3528",  "lobortis.quam.a@placerat.edu",
//		 "Karyn Graves", "437-9326 Mauris St.",  "United Kingdom", "+44 472 9377-8071",  "mauris@laciniaat.org",
//		 "Sandra Larsen", "Ap #988-2669 Eu, St.",  "United Kingdom", "+44 401 6414-8878",  "nibh@convallisconvallis.ca",
//		 "Hedy Cummings", "4356 Mollis St.",  "United Kingdom", "+44 697 8470-5117",  "eget.nisi.dictum@primisinfaucibus.org",
//		 "Mechelle Willis", "823-8834 Lobortis Av.",  "United Kingdom", "+44 208 4829-4172",  "malesuada.Integer.id@Vestibulum.net",
		 "Charles Solomon", "P.O. Box 895, 8775 Pharetra Street",  "United Kingdom", "+44 665 8787-1114",  "Curabitur@eu.co.uk"};
		
		for (int idx = 0; idx < data.length; idx+=5) {
			Contact c 		= new Contact();
			c.name			= data[idx];
			c.address		= data[idx+1];
			c.country		= data[idx+2];
			c.phone			= data[idx+3];
			c.email			= data[idx+4];
			_container.put(c.getKey(), c);
		}
	}
}
