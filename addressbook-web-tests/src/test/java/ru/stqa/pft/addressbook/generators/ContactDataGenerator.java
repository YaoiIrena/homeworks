package ru.stqa.pft.addressbook.generators;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.thoughtworks.xstream.XStream;
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

    @Parameter(names = "-d", description = "Data format")
    public String format;

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
        if (format.equals("csv")){
            saveAsCsv(contacts, new File(file));
        } else if (format.equals("xml")){
            saveAsXml(contacts, new File(file));
        } else if (format.equals("json")){
            saveAsJson(contacts, new File(file));
        } else {
            System.out.println("Неподдерживаемый формат " + format);
        }
    }

    private void saveAsJson(List<KontactData> contacts, File file) throws IOException {
        Gson gson = new GsonBuilder().setPrettyPrinting().excludeFieldsWithoutExposeAnnotation().create();
        String json = gson.toJson(contacts);
        try (Writer writer = new FileWriter(file)){
            writer.write(json);
        }
    }

    private void saveAsXml(List<KontactData> contacts, File file) throws IOException {
        XStream xstream = new XStream();
        String xml = xstream.toXML(contacts);
        try (Writer writer = new FileWriter(file)){
            writer.write(xml);
        }
    }

    private void saveAsCsv(List<KontactData> contacts, File file) throws IOException {
        System.out.println(new File(".").getAbsolutePath());
        try(Writer writer = new FileWriter(file)) {
            for(KontactData contact:contacts)
            {
                writer.write(String.format("%s;%s;%s;%s;%s;%s;%s;%s;%s", contact.getLastname(), contact.getFirstname(),
                        contact.getAddress(), contact.getHome(), contact.getMobile(), contact.getWork(),
                        contact.getEmail(), contact.getEmail2(), contact.getEmail3()));
            }
        }
    }

    private List<KontactData> generateContacts(int count) {
        List<KontactData> contacts = new ArrayList<KontactData>();
        for (int i = 0; i < count; i++){
            contacts.add(new KontactData().withLastname(String.format("test %s", i)).withFirstname(String.format("test %s", i))
                    .withAddress(String.format("test %s", i)).withHome(String.format("+7 %s", i)).withMobile(String.format("+7 %s", i))
                    .withWork(String.format("+7 %s", i)).withEmail(String.format("e%s@gmail.com", i))
                    .withEmail2(String.format("e%s@gmail.com", i)).withEmail3(String.format("e%s@gmail.com", i))
                    .withGroup(String.format("test 0", i)));
        }
        return contacts;
    }
}
