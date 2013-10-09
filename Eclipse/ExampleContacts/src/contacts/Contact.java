package contacts;

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
 *  Contact bean
 */
public class Contact 
{
	public String name;
	public String address;
	public String country;
	public String phone;
	public String email;

	public Contact getCopy() {
		Contact clone = new Contact();
		clone.name = name;
		clone.address = address;
		clone.country = country;
		clone.phone = phone;
		clone.email = email;
		return clone;
	}

	public void updateBy(Contact modified)  {
		if (modified == null)
			return;
		
		//if (modified.name.equals(this.name))
		//	System.out.println("Could be an error");
		
		// name is a key ... 
		// do not change in case of update
		address = modified.address;
		country = modified.country;
		phone = modified.phone;
		email = modified.email;
    }

	public String getKey() {
		return name.toLowerCase();
	}
}
