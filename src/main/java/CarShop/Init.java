package CarShop;

import CarShop.models.dtos.BrandDTO;
import CarShop.models.dtos.ModelDTO;
import CarShop.models.dtos.UserDTO;
import CarShop.models.enums.Category;
import CarShop.models.enums.Engine;
import CarShop.models.enums.Role;
import CarShop.models.enums.Transmission;
import CarShop.services.Interf.BrandService;
import CarShop.services.Interf.ModelService;
import CarShop.services.Interf.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;

import org.springframework.stereotype.Component;

@Component
public class Init implements CommandLineRunner {

    private final BrandService brandService;
    private final ModelService modelService;
    private final UserService userService;

    @Autowired
    public Init(BrandService brandService, ModelService modelService, UserService userService) {
        this.brandService = brandService;
        this.modelService = modelService;
        this.userService = userService;
    }

    @Override
    public void run(String... args) throws Exception {
        create();
    }

    private void create() {

        BrandDTO Mers = new BrandDTO();
        Mers.setName("Mercedes");
        Mers.setImage_url("/img/logoBrand/mersLogo.jpg");
        Mers.setTop(4);
        Mers = brandService.addBrand(Mers);

        BrandDTO BMW = new BrandDTO();
        BMW.setName("BMW");
        BMW.setImage_url("/img/logoBrand/bmwLogo.jpg");
        BMW = brandService.addBrand(BMW);

        BrandDTO Audi = new BrandDTO();
        Audi.setName("Audi");
        Audi.setImage_url("/img/logoBrand/audiLogo.jpg");
        Audi = brandService.addBrand(Audi);

        BrandDTO Lexus = new BrandDTO();
        Lexus.setName("Lexus");
        Lexus.setImage_url("/img/logoBrand/lexLogo.jpg");
        Lexus.setTop(3);
        Lexus = brandService.addBrand(Lexus);

        BrandDTO Nissan = new BrandDTO();
        Nissan.setName("Nissan");
        Nissan.setImage_url("/img/logoBrand/nisLogo.jpg");
        Nissan = brandService.addBrand(Nissan);

        BrandDTO Mazda = new BrandDTO();
        Mazda.setName("Mazda");
        Mazda.setImage_url("/img/logoBrand/mazdaLogo.jpg");
        Mazda = brandService.addBrand(Mazda);

        BrandDTO Honda = new BrandDTO();
        Honda.setName("Honda");
        Honda.setImage_url("/img/logoBrand/hondaLogo.jpg");
        Honda = brandService.addBrand(Honda);

        BrandDTO Toyota = new BrandDTO();
        Toyota.setName("Toyota");
        Toyota.setImage_url("/img/logoBrand/toyLogo.jpg");
        Toyota.setTop(2);
        Toyota = brandService.addBrand(Toyota);

        BrandDTO Reno = new BrandDTO();
        Reno.setName("Reno");
        Reno.setImage_url("/img/logoBrand/renoLogo.png");
        Reno = brandService.addBrand(Reno);

        BrandDTO Hyundai = new BrandDTO();
        Hyundai.setName("Hyundai");
        Hyundai.setImage_url("/img/logoBrand/hyunLogo.jpg");
        Hyundai = brandService.addBrand(Hyundai);

        ModelDTO m1 = new ModelDTO();
        m1.setName("Mercedes-Benz GLE 400 d");
        m1.setImage_url("/img/models/Mercedes/m1.png");
        m1.setCategory(Category.Car);
        m1.setEngine(Engine.Diesel);
        m1.setTransmission(Transmission.Automatic);
        m1.setPrice(93903.18);
        m1.setBrand(Mers);
        modelService.addModel(m1);

        ModelDTO m2 = new ModelDTO();
        m2.setName("Mercedes-Benz GLE 450");
        m2.setImage_url("/img/models/Mercedes/m2.png");
        m2.setCategory(Category.Car);
        m2.setEngine(Engine.Diesel);
        m2.setTransmission(Transmission.Automatic);
        m2.setPrice(96301.96);
        m2.setBrand(Mers);
        modelService.addModel(m2);

        ModelDTO m3 = new ModelDTO();
        m3.setName("Mercedes-Benz G-Класс AMG 63 AMG");
        m3.setImage_url("/img/models/Mercedes/m3.png");
        m3.setCategory(Category.Car);
        m3.setEngine(Engine.Diesel);
        m3.setTransmission(Transmission.Automatic);
        m3.setPrice(215856.26);
        m3.setBrand(Mers);
        modelService.addModel(m3);

        ModelDTO m4 = new ModelDTO();
        m4.setName("Mercedes-Benz Maybach S-Класс 680");
        m4.setImage_url("/img/models/Mercedes/m4.png");
        m4.setCategory(Category.Car);
        m4.setEngine(Engine.Diesel);
        m4.setTransmission(Transmission.Automatic);
        m4.setPrice(459972.93);
        m4.setBrand(Mers);
        modelService.addModel(m4);

        ModelDTO b1 = new ModelDTO();
        b1.setName("BMW X5 40i");
        b1.setImage_url("/img/models/BMW/b1.png");
        b1.setCategory(Category.Car);
        b1.setEngine(Engine.Diesel);
        b1.setTransmission(Transmission.Automatic);
        b1.setPrice(185687.6);
        b1.setBrand(BMW);
        modelService.addModel(b1);

        ModelDTO b2 = new ModelDTO();
        b2.setName("BMW X6 30d");
        b2.setImage_url("/img/models/BMW/b2.png");
        b2.setCategory(Category.Car);
        b2.setEngine(Engine.Diesel);
        b2.setTransmission(Transmission.Automatic);
        b2.setPrice(122474.8);
        b2.setBrand(BMW);
        modelService.addModel(b2);

        ModelDTO b3 = new ModelDTO();
        b3.setName("BMW X5 40d");
        b3.setImage_url("/img/models/BMW/b3.png");
        b3.setCategory(Category.Car);
        b3.setEngine(Engine.Diesel);
        b3.setTransmission(Transmission.Automatic);
        b3.setPrice(168191.2);
        b3.setBrand(BMW);
        modelService.addModel(b3);

        ModelDTO b4 = new ModelDTO();
        b4.setName("BMW X5 30d");
        b4.setImage_url("/img/models/BMW/b4.png");
        b4.setCategory(Category.Car);
        b4.setEngine(Engine.Diesel);
        b4.setTransmission(Transmission.Automatic);
        b4.setPrice(144475.11);
        b4.setBrand(BMW);
        modelService.addModel(b4);

        UserDTO admin = new UserDTO();
        admin.setFirstName("Замуруев");
        admin.setUserName("Роман");
        admin.setLastName("Романович");
        admin.setLogin("admin");
        admin.setPassword("admin");
        admin.setRole(Role.Admin.name());
        userService.addUser(admin);

        UserDTO user = new UserDTO();
        user.setFirstName("Замуруева");
        user.setUserName("Екатерина");
        user.setLastName("Александровна");
        user.setLogin("user");
        //user.setIs_active(true);
        user.setPassword("user");
        user.setRole(Role.User.name());
        userService.addUser(user);
    }
}

