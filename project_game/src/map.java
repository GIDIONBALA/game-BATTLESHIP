import java.util.Scanner;

public class map {
    public static void main(String[] args) {

        Scanner TitikKoordinat = new Scanner(System.in);

        System.out.print("masukkan sumbu x :");
        int sumbuX = TitikKoordinat.nextInt();

        System.out.print("masukkan sumbu y :");
        int sumbuY = TitikKoordinat.nextInt();

        System.out.println("titik koordinat yang kamu masukkan adalah ("+ sumbuX + " , 4"+ sumbuY +")");
    }
}
