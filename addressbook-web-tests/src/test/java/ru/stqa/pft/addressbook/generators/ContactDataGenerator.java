package ru.stqa.pft.addressbook.generators;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import ru.stqa.pft.addressbook.model.KontactData;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class ContactDataGenerator {

    @Parameter(names = "-c", description = "Contact count")
    public int count;

    @Parameter(names = "-f", description = "Target file")
    public String file;

    public static void main(String[] args) throws IOException {
        ContactDataGenerator generator = new ContactDataGenerator();
        JCommander jCommander = new JCommander(generator);
        try {
            jCommander.parse(args);
        } catch (ParameterException ex){
            jCommander.usage();
            return;
        }
        generator.run();

    }

    private void run() throws IOException {
        List<KontactData> contacts = generateContacts(count);
        save(contacts, new File(file));
    }

    private void save(List<KontactData> contacts, File file) throws IOException {
        System.out.println(new File(".").getAbsolutePath());
        Writer writer = new FileWriter(file);
        for (KontactData contact: contacts){
            writer.write(String.format("%s;%s;%s;%s;%s;%s;%s;%s;%s", contact.getLastname(), contact.getFirstname(),
                    contact.getAddress(), contact.getEmail(), contact.getEmail2(),
                    contact.getEmail3(), contact.getHome(), contact.getMobile(), contact.getFax()));
        }
        writer.close();
        }

    private List<KontactData> generateContacts(int count) {
        List<KontactData> contacts = new ArrayList<KontactData>();
        for (int i = 0; i < count; i++){
            contacts.add(new KontactData().withLastname(String.format("test %s", i)).withFirstname(String.format("test %s", i))
                    .withAddress(String.format("test %s", i)).withEmail(String.format("e%s@gmail.com", i))
                    .withEmail2(String.format("e%s@gmail.com", i)).withEmail3(String.format("e%s@gmail.com", i))
                    .withHome(String.format("+7 %s", i)).withMobile(String.format("+7 %s", i)).withFax(String.format("+7 %s", i)));
        }
        return contacts;
    }
}
