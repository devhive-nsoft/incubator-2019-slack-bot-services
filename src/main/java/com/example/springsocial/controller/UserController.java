package com.example.springsocial.controller;

import com.example.springsocial.exception.ResourceNotFoundException;
import com.example.springsocial.model.User;
import com.example.springsocial.model.UserSettings;
import com.example.springsocial.repository.UserRepository;
import com.example.springsocial.repository.UserSettingsRepository;
import com.example.springsocial.security.CurrentUser;
import com.example.springsocial.security.UserPrincipal;

import java.awt.print.Pageable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class UserController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserSettingsRepository userSettingsRepository;
    @Autowired
    MessageSource messageSource;
    
    @RequestMapping(value = "auth/get-greeting", method = RequestMethod.GET)
    public List<String> greeting() {
      /**
       *   @LocaleContextHolder.getLocale()
       *  Return the Locale associated with the given user context,if any, or the system default Locale otherwise.
       *  This is effectively a replacement for Locale.getDefault(), able to optionally respect a user-level Locale setting.
       */
    	List<String> data = new ArrayList<String>();
    	data.add(messageSource.getMessage("settings", null, LocaleContextHolder.getLocale()));
    	data.add(messageSource.getMessage("theme", null, LocaleContextHolder.getLocale()));
    	data.add(messageSource.getMessage("select.color", null, LocaleContextHolder.getLocale()));
    	data.add(messageSource.getMessage("select.language", null, LocaleContextHolder.getLocale()));
    	data.add(messageSource.getMessage("language", null, LocaleContextHolder.getLocale()));
     return data;
    }
    @GetMapping("/user/me")
    @PreAuthorize("hasRole('USER')or hasRole('ADMIN')")
    public User getCurrentUser(@CurrentUser UserPrincipal userPrincipal) {
        return userRepository.findById(userPrincipal.getId())
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", userPrincipal.getId()));
    }
    @GetMapping("/user/delete")
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteUser(@RequestBody User user) {
    	userRepository.delete(user);
    		
    }
   
   
    @PostMapping("/user/color")
    @PreAuthorize("hasRole('USER')or hasRole('ADMIN')")
    public UserSettings setUserColor( @RequestBody UserSettings userSettings,@CurrentUser UserPrincipal userPrincipal) {
    	User user=userRepository.findById(userPrincipal.getId())
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", userPrincipal.getId()));
    	UserSettings usersettings2=userSettingsRepository.findById(user.getUserSettings().getId()).orElseThrow(() -> new ResourceNotFoundException("UserSettings", "id", userSettings.getId()));
    	//userSetttings.setId(user.getUserSettings().getId())
    	usersettings2.setTheme(userSettings.getTheme());
    	usersettings2.setLanguage(userSettings.getLanguage());
    	UserSettings result=userSettingsRepository.save(usersettings2);
    	return result;
    	//userRepository.save(user);
    	
    	
    }
   
    
    
}

