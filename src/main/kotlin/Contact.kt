class Contact (var firstname: String, var lastname: String, var phoneNumbers: MutableList<String>, var emailAddresses: MutableList<String>) {
    // Edit
    fun edit(fname: String = "", lname: String = "", pnumbers: MutableList<String> = mutableListOf(), emails: MutableList<String> = mutableListOf()){
        if (fname != "")
            firstname = fname
        if (lname != "")
            lastname = lname
        if(pnumbers.isNotEmpty())
            phoneNumbers = pnumbers
        if(emails.isNotEmpty())
            emailAddresses = emails
    }

    fun name(): String {
        return firstname + " " + lastname
    }

    fun displayContact(){
        displayName()
        displayPhone()
        displayEmails()
    }

    fun displayName(){
        println("Name : " + name())
    }

    fun displayPhone(){
        print("Phone number(s) : ")
        for (p in phoneNumbers){
            print(p)
            if(phoneNumbers.indexOf(p) != phoneNumbers.size-1) print(" | ") else print("\n")
        }
    }

    fun displayEmails(){
        print("Email : ")
        for (e in emailAddresses) {
            print(e)
            if(emailAddresses.indexOf(e) != emailAddresses.size-1) print(" | ") else print("\n")
        }
    }

}