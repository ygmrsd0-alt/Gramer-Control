package gramer;

import java.io.*;
import java.util.*;
import javax.swing.*;

public class Gramer {

    public static void main(String[] args) throws FileNotFoundException, IOException {

        String[] ozneKumesi = {"Ben", "Sen", "Hasan", "Nergis", "Nurşah", "Elif", "Abdulrezzak", "Şehribanu", "Zeynelabidin", "Naki", "Furkan"};
        String[] nesneKumesi = {"Bahçe", "Okul", "Park", "Sınıf", "Yarın", "Pazartesi", "Salı", "Çarşamba", "Perşembe", "Cuma", "Cumartesi", "Pazar", "Merkez", "Ev", "Kitap", "Defter", "Güneş", "Beydağı", "Kantin"};
        String[] yuklemKumesi = {"Gitmek", "Gelmek", "Okumak", "Yazmak", "Yürümek", "Görmek"};

        JFileChooser chooser = new JFileChooser();
        chooser.setDialogTitle("Dosya seçin");

        if (chooser.showOpenDialog(null) != JFileChooser.APPROVE_OPTION) {
            System.out.println("Dosya seçilmedi.");
            return;
        }

        File dosya = chooser.getSelectedFile();

        ArrayList<String> herSey = new ArrayList<>();
        Scanner dosyaOkuyucu = new Scanner(dosya);

        while (dosyaOkuyucu.hasNext()) {
            herSey.add(dosyaOkuyucu.next());
        }
        dosyaOkuyucu.close();

        if (herSey.isEmpty()) {
            System.out.println("HAYIR");
            return;
        }

        boolean genelUygunluk = true;
        ArrayList<String> geciciCumle = new ArrayList<>();

        for (String parca : herSey) {
            if (parca.equals(".")) {
                if (geciciCumle.size() == 3) {
                    String o = geciciCumle.get(0);
                    String n = geciciCumle.get(1);
                    String y = geciciCumle.get(2);

                    if (!listeKontrol(ozneKumesi, o) ||
                        !listeKontrol(nesneKumesi, n)||
                        !listeKontrol(yuklemKumesi, y)) {
                        genelUygunluk = false;
                        break;
                    }
                } else {
                    genelUygunluk = false;
                    break;
                }
                geciciCumle.clear();
            } else {
                geciciCumle.add(parca);
            }
        }

        if (geciciCumle.size() != 0 || !herSey.get(herSey.size() - 1).equals(".")) {
            genelUygunluk = false;
        }

        if (genelUygunluk) {
            System.out.println("EVET");
        } else {
            System.out.println("HAYIR");
        }

    }

    public static boolean listeKontrol(String[] dizi, String aranan) {
        String kucukAranan = aranan.toLowerCase();

        for (String eleman : dizi) {
            if (eleman.toLowerCase().equals(kucukAranan)) {
                return true;
            }
        }
        return false;
    }
}
