package com.wherepeople.spring.mvc.service;

import com.wherepeople.spring.mvc.model.login.AccessToken;
import com.wherepeople.spring.mvc.model.person.Person;
import com.wherepeople.spring.mvc.repository.AccessTokenRepository;
import com.wherepeople.spring.mvc.repository.PersonRepository;
import com.wherepeople.spring.mvc.util.ImageUtil;
import com.wherepeople.spring.mvc.util.WebServiceUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.servlet.ServletContext;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.security.MessageDigest;
import java.util.Calendar;
import java.util.List;

/**
 * Created by yuriy on 02.04.15.
 */
@Service
public class UserServiceImpl implements UserService, ServletContextAware {
    public static final int AVATAR_SIZE_IN_PIXELS = 70;
    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private AccessTokenRepository accessTokenRepository;

    private ServletContext servletContext;

    @Override
    public List<Person> getAllUsers() {
        System.out.println("getAllUsers");
        return personRepository.findAll();
    }

    @Override
    public Person createUser(Person person) throws Exception {
        System.out.print("trying to create person "+person.getUsername());
        if (personRepository.findOneByUsername(person.getUsername()) != null){
            throw new Exception(String.format("Username %s is already in use", person.getUsername()));
        }
        return personRepository.save(person);
    }

    @Override
    public Person changeAvatar(Person person, MultipartFile avatar) throws Exception {
        System.out.println("trying to attach avatar for " + person.getUsername());
        if (avatar.isEmpty()){
            return person;
        }
        String contentType = avatar.getContentType().toLowerCase();
        if (!contentType.startsWith("image/")) {
            throw new Exception(String.format("%s format is unsupported as avatar", contentType));
        }
        String fileName = String.format("%s.png", person.getUsername());
        saveAvatar(fileName, avatar.getBytes());
        person.setAvatar(fileName);
        return personRepository.save(person);
    }

    private void saveAvatar(String fileName, byte[] bytes) throws IOException {
        String avatarDirectory = servletContext.getRealPath("/static/avatar");
        File file = new File(avatarDirectory, fileName);
        file.getParentFile().mkdirs();
        System.out.println("trying to save avatar of" + bytes.length + " bytes to " + file.getAbsolutePath());
        BufferedImage image = ImageIO.read(new ByteArrayInputStream(bytes));
        BufferedImage centralSquare;
        if (image.getWidth() > image.getHeight()) {
            centralSquare = image.getSubimage( (image.getWidth() - image.getHeight()) / 2, 0, image.getHeight(), image.getHeight() );
        } else {
            centralSquare = image.getSubimage( 0, (image.getHeight() - image.getWidth()) / 2, image.getWidth(), image.getWidth() );
        }
        Image thumbnail = centralSquare.getScaledInstance(AVATAR_SIZE_IN_PIXELS, AVATAR_SIZE_IN_PIXELS, Image.SCALE_SMOOTH);

        BufferedImage rounded = ImageUtil.makeRounded(thumbnail, new ImageUtil.EllipseRounder());
        ImageIO.write(rounded, "png", file);
    }

    @Override
    public AccessToken login(Person person) throws Exception {
        if (WebServiceUtil.isEmptyOrNull(person.getUsername()) || WebServiceUtil.isEmptyOrNull(person.getPassword())){
            throw new Exception("Username or password is empty");
        }
        if (personRepository.findOneByUsernameAndPassword(person.getUsername(), person.getPassword()) != null) {
            AccessToken accessToken = new AccessToken();
            accessToken.setUsername(person.getUsername());
            byte[] md5s = MessageDigest.getInstance("MD5").digest((person.getUsername() + person.getPassword() + Calendar.getInstance().getTime().getTime()).getBytes());
            accessToken.setAccessToken(WebServiceUtil.bytesToString(md5s));
            accessTokenRepository.save(accessToken);
            return accessToken;
        } else {
            throw new Exception("Incorrect login/password");
        }
    }

    @Override
    public Person createUser(Person person, MultipartFile avatar) throws Exception {
        person = createUser(person);
        return changeAvatar(person, avatar);
    }

    @Override
    public void setServletContext(ServletContext servletContext) {
        this.servletContext = servletContext;
    }

}
