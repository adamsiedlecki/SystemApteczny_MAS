package net.asiedlecki.system.apteczny;


import net.asiedlecki.system.apteczny.serwisy.DaneInicjalne;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SystemAptecznyMain {
    public static void main(String[] args) {
        SpringApplication.run(SystemAptecznyMain.class, args);

        DaneInicjalne.zapiszInicjalneDane();
    }
}
