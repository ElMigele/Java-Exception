import java.io.FileWriter;
import java.lang.instrument.IllegalClassFormatException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.IllformedLocaleException;

public class Model {
    
    String[] blocks; // 0-2: FIO; 3: Dr; 4: tel; 5: sex

    public Model()
    {
        blocks = new String[6];
    }

    void Read(String input) throws Exception {
        String[] inputs = input.split(" ");
        if (inputs.length != 6)
            throw new IndexOutOfBoundsException("Некорректно. значений не 6!");
        for (int i = 0; i < inputs.length; i++) {
            switch(i) {
                case 0:
                case 1:
                case 2:
                for (int x = 0; x < inputs[i].length(); x++) {
                    if (Character.isDigit(inputs[i].charAt(x))) {
                        throw new IllegalClassFormatException("Некорректно. в ФИО есть цифры!");
                    }
                }
                    break;
                case 3:
                    try {
                        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd.MM.yyyy");
                        LocalDate date = LocalDate.parse(inputs[i], format);
                        inputs[i] = date.toString();
                    } catch (Exception e) {
                        throw new IllformedLocaleException("Некорректный формат даты рождения. Должно быть: dd.mm.yyyy");
                    }               
                    break;
                case 4:
                for (int x = 0; x < inputs[i].length(); x++) {
                    if (!Character.isDigit(inputs[i].charAt(x))) {
                        throw new IllegalClassFormatException("Некорректно. В номере не должно быть ничего кроме цифр!");
                    }
                }
                    break;
                case 5:
                    if (!inputs[i].equals("m")
                    && !inputs[i].equals("f"))
                    {
                        throw new IllformedLocaleException("Некорректный формат пола человека. Должно быть m / f");
                    }
                    break;
                default:
                    break;
            }

            blocks[i] = inputs[i];
        }
        Write();
    }

    void Write(){
        try(FileWriter writer = new FileWriter(blocks[0], true)) {
            for (int i = 0; i < blocks.length; i++) {
                writer.write("<" + blocks[i] + ">");
                if (i != blocks.length - 1){
                    writer.write(" ");
                }
            }
            writer.append('\n');
        } catch (Exception e) {

        }
    }
}
