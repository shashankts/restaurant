package com.solosos.restaurant.service;

import com.solosos.restaurant.dto.LoginRequest;
import com.solosos.restaurant.dto.RestaurantAddRequest;
import com.solosos.restaurant.exception.RestaurantNotFoundException;
import com.solosos.restaurant.model.*;
import com.solosos.restaurant.repository.MenuItemRepository;
import com.solosos.restaurant.repository.RestaurantRepository;
import javaxt.io.Image;
import lombok.AllArgsConstructor;
import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class RestaurantService {

    private final RestaurantRepository restaurantRepository;
    private final MenuItemRepository menuItemRepository;
    private final Login login;
    private final ITesseract iTesseract = new Tesseract();

    @Transactional
    public Restaurant addRestaurant(RestaurantAddRequest restaurantAddRequest) throws IOException, TesseractException {
        Restaurant restaurant = new Restaurant();
        restaurant.setGstin(restaurantAddRequest.getGstin());
        restaurant.setRestaurantName(restaurantAddRequest.getRestaurantName());
        restaurant.setPhoneNumber(restaurantAddRequest.getPhoneNumber());
        restaurant.setEmailId(restaurantAddRequest.getEmailId());
        restaurant.setAlternatePhoneNumber(restaurantAddRequest.getAlternatePhoneNumber());
        restaurant.setFssai(restaurantAddRequest.getFssai());
        restaurant.setMenuItemList(addMenu(restaurantAddRequest.getImageFile(),restaurantAddRequest.getRestaurantName()));
        restaurant.setContactPersonName(restaurantAddRequest.getContactPersonName());
        restaurant.setLinkedWith(addLinkedWith(restaurantAddRequest.getLinkedTo()));
        restaurant.setContactPersonPhoneNumber(restaurantAddRequest.getContactPersonPhoneNumber());
        restaurant.setContactPersonRole(restaurantAddRequest.getContactPersonRole());
        restaurant.setRestaurantPic(addRestaurantPic(restaurantAddRequest.getRestaurantImage(),restaurantAddRequest.getRestaurantName()));
        restaurant.setAddress(restaurantAddRequest.getAddress());
        restaurantRepository.save(restaurant);
        return restaurant;
    }


    RestaurantPic addRestaurantPic(MultipartFile restaurantImage, String restaurantName) throws IOException {
        RestaurantPic restaurantPic = new RestaurantPic();
        String folder = "src/main/resources/images/restaurantpicture/";
        byte[] bytes = restaurantImage.getBytes();
        Path path = Paths.get(folder+restaurantName+".jpg");
        Files.write(path,bytes);
        Image image = new Image("src/main/resources/images/restaurantpicture/"+restaurantName+".jpg");
        restaurantPic.setPath(folder+restaurantName+".jpg");
        double[] gpsCoordinate = image.getGPSCoordinate();
        restaurantPic.setLongitude(gpsCoordinate[0]);
        restaurantPic.setLatitude(gpsCoordinate[1]);
        return  restaurantPic;

    }


    private List<Linked> addLinkedWith(String[] linkedTo) {
        List<Linked> linkedList = new ArrayList<>();
        for (String linked:
             linkedTo) {
            Linked link = new Linked();
            link.setLinkedTo(linked);
            linkedList.add(link);
        }
        return linkedList;
    }

    public ResponseEntity<String> login(LoginRequest loginRequest) {
        if(loginRequest!=null)
            if(loginRequest.getUserName().equals(login.getUserName()))
                if(loginRequest.getPassword().equals(login.getPassword()))
                        return new ResponseEntity<>("Successfully Logged in", HttpStatus.OK);

        return new ResponseEntity<>("User Not Found",HttpStatus.NOT_FOUND);

    }

    public List<Restaurant> getRestaurants() {
        return restaurantRepository.findAll();
    }

    public Optional<Restaurant> deleteRestaurant(long id) throws RestaurantNotFoundException{

        if(!restaurantRepository.existsById(id))
            throw new RestaurantNotFoundException("Restaurant With "+id+" Not Found");
       Optional<Restaurant> restaurant = restaurantRepository.findById(id);
       restaurantRepository.deleteById(id);
       return restaurant;
    }

    public List<MenuItem> addMenu(MultipartFile imageFile, String restaurantName) throws IOException, TesseractException {
        String folder = "src/main/resources/images/menu/";
        byte[] bytes = imageFile.getBytes();
        Path path = Paths.get(folder+"menu_"+restaurantName+".png");
        Files.write(path,bytes);
        iTesseract.setDatapath("tessdata");
        File file = new File("src/main/resources/images/menu/menu_"+restaurantName+".png");
        String result = iTesseract.doOCR(file);
        result = result.replaceAll("[^0-9a-zA-Z]","");
        List<MenuItem> menuItemsList = new ArrayList<>();
        String[] dishName = result.split("[0-9]+");
        String[] dishPrice = result.split("[a-zA-Z]+");
        for(int i = 0;i<dishName.length;i++)
        {
            MenuItem menuItem = new MenuItem();
            menuItem.setDishName(dishName[i].toLowerCase());
            menuItem.setPrice(dishPrice[i+1]);
            menuItemsList.add(menuItem);
        }
        return menuItemsList;
    }

}
