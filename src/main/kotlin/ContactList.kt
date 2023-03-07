class ContactList (var contacts: MutableList<Contact>) {
    fun add (newcontact: Contact){
        contacts.add(newcontact)
    }

    // Remove
    fun removeAt(index: Int){
        contacts.removeAt(index)
    }

    // Show
    fun display(){
        contacts.sortBy { it.lastname } // Sort by alphabetic order
        //Order index
        for (contact in contacts){
            contact.displayContact()
            println("----------------------------------")
        }
    }
}