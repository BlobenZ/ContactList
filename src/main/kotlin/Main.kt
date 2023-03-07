import java.io.Console
import java.io.File

var contactList = ContactList(mutableListOf())
val filePath = "data.txt"

fun main(args: Array<String>) {
    loadFromFile()
    menu()
    saveToFile()
}

fun selectContact():Int { //Return index of contact in contactList
    clear()
    println("<--------------------->")
    println("   Contact Selection   ")
    println("<--------------------->")
    for (c in contactList.contacts){
        println((contactList.contacts.indexOf(c) + 1).toString() + " : " + c.name())
    }
    println("<--------------------->")
    print("> ")
    var index = readln().toIntOrNull()
    if (index == null) return -1 else return (index - 1)

}

fun phoneSelection(contact: Contact): Int{
    clear()
    println("<--------------------->")
    println(" Phone number Selection")
    println("<--------------------->")
    for (p in contact.phoneNumbers){
        println((contact.phoneNumbers.indexOf(p) + 1).toString() + " : " + p)
    }
    println("<--------------------->")
    print("> ")
    var index = readln().toIntOrNull()
    if (index == null) return -1 else return (index - 1)
}

fun emailSelection(contact: Contact): Int{
    clear()
    println("<--------------------->")
    println("    Email Selection    ")
    println("<--------------------->")
    for (p in contact.emailAddresses){
        println((contact.emailAddresses.indexOf(p) + 1).toString() + " : " + p)
    }
    println("<--------------------->")
    print("> ")
    var index = readln().toIntOrNull()
    if (index == null) return -1 else return (index - 1)
}

fun pause(){
    println("Press enter to continue...")
    readln()
}

fun clear(){
    for (i in 1..20)
        println("")
}

fun saveToFile(){
    File(filePath).printWriter().use { out ->
        for (c in contactList.contacts) {
            out.println("F:" + c.firstname)
            out.println("L:" + c.lastname)
            for (p in c.phoneNumbers)
                out.println("P:" + p)
            for (e in c.emailAddresses)
                out.println("E:" + e)
        }
    }
}

fun loadFromFile(){
    var fileStrings: List<String> = File(filePath).readLines()
    var newContact: Boolean
    var startIndex = 0
    var tempStartIndex = 0

    while (true){
        //Check if done reading
        if(startIndex >= fileStrings.size)
            break

        //Create new contact
        var c = Contact("", "", mutableListOf(), mutableListOf())

        newContact = true //Set newContact

        for (i in startIndex until fileStrings.size){
            var line = fileStrings[i]
            var prefix = line.get(0)
            when (prefix){
                'F' -> {
                    if (!newContact)
                        break
                    c.edit(line.substring(2))
                    newContact = false
                }
                'L' -> {
                    c.edit("", line.substring(2))
                }
                'P' -> {
                    c.phoneNumbers.add(line.substring(2))
                }
                'E' -> {
                    c.emailAddresses.add(line.substring(2))
                }
            }
            tempStartIndex = i
        }
        startIndex = tempStartIndex + 1
        contactList.add(c)
    }


}

fun menu(){
    while (true) {
        clear()
        println("<--------------------->")
        println(" Contact Jakobs mamma  ")
        println("<--------------------->")
        println("1. Show contact list")
        println("2. Add new contact")
        println("3. Remove contact")
        println("4. Edit contact")
        println("5. Exit")
        println("<--------------------->")
        print("> ")
        var input: Int = readln().toInt()
        when (input) {
            1 -> { // Show
                contactList.display()
                pause()
            }

            2 -> { // Add
                println("Create new contact:")
                print("First name: ")
                var fname = readln()
                print("Last name: ")
                var lname = readln()
                print("Phone number: ")
                var pnumbers = mutableListOf<String>()
                while (true){
                    var r = readln()
                    if(r == "0")
                        break;
                    pnumbers.add(r)
                    print("Another phone number ( 0 = No more ): ")
                }
                print("Email address: ")
                var emails = mutableListOf<String>()
                while (true){
                    var r = readln()
                    if(r == "0")
                        break;
                    emails.add(r)
                    print("Another email address ( 0 = No more ): ")
                }

                contactList.add(Contact(fname, lname, pnumbers, emails))
                println("Contact : " + fname + " " + lname + " has been added")
            }

            3 -> { // Remove
                var index = selectContact()
                contactList.removeAt(index)
            }

            4 -> { // Edit
                var index = selectContact()
                var c = contactList.contacts[index]
                while (true){
                    clear()
                    println("<--------------------->")
                    println(" Edit contact: " + c.name())
                    println("<--------------------->")
                    println("1. First name")
                    println("2. Last name")
                    println("3. Phone numbers")
                    println("4. Emails")
                    println("5. Back")
                    println("<--------------------->")
                    print("> ")
                    var editinput = readln().toIntOrNull()
                    if (editinput == null) {
                        println("Input must be a number and be between 1 and 4")
                        continue
                    }

                    when(editinput){
                        1 -> {
                            //Firstname
                            clear()
                            println("Current: " + c.name())
                            print("Enter new first name: ")
                            var fname = readln()
                            c.edit(fname)
                        }
                        2 -> {
                            //Lastname
                            clear()
                            println("Current: " + c.name())
                            print("Enter new last name: ")
                            var lname = readln()
                            c.edit("", lname)
                        }
                        3 -> {
                            //Phone numbers
                            while (true) {
                                clear()
                                println("Current phone numbers: ")
                                for (p in c.phoneNumbers)
                                    println(p)
                                println("1. Add")
                                println("2. Remove")
                                println("3. Back")
                                print("> ")
                                var input = readln().toInt()
                                when (input) {
                                    1 -> {
                                        clear()
                                        println("New phone number : ")
                                        var newp = readln()
                                        c.phoneNumbers.add(newp)
                                    }

                                    2 -> {
                                        var index = phoneSelection(c)
                                        c.phoneNumbers.removeAt(index)
                                    }

                                    3 -> {
                                        break;
                                    }
                                }
                            }
                        }
                        4 -> {
                            //Emails
                            while (true) {
                                clear()
                                println("Current email addresses: ")
                                for (e in c.emailAddresses)
                                    println(e)
                                println("1. Add")
                                println("2. Remove")
                                println("3. Back")
                                print("> ")
                                var input = readln().toInt()
                                when (input) {
                                    1 -> {
                                        clear()
                                        println("New email address : ")
                                        var newe = readln()
                                        c.phoneNumbers.add(newe)
                                    }

                                    2 -> {
                                        var index = emailSelection(c)
                                        c.emailAddresses.removeAt(index)
                                    }

                                    3 -> {
                                        break
                                    }
                                }
                            }
                        }
                        5 -> {
                            //Go back to menu
                            break
                        }
                    }

                }
            }

            5 -> { // Exit
                break
            }
        }
    }
}