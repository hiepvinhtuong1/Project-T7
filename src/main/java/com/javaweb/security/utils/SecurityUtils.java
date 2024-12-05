package com.javaweb.security.utils;

import com.javaweb.model.dto.MyUserDetail;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.ArrayList;
import java.util.List;

public class SecurityUtils {

    public static MyUserDetail getPrincipal() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        // Kiểm tra nếu Authentication không phải null và đã đăng nhập
        if (authentication.getPrincipal() != "anonymousUser" && authentication.isAuthenticated()) {
            return (MyUserDetail) authentication.getPrincipal();
        }

        // Nếu chưa đăng nhập, có thể trả về một đối tượng mặc định hoặc null
        return null; // Hoặc trả về null, tùy thuộc vào yêu cầu
    }

    public static List<String> getAuthorities() {
        List<String> results = new ArrayList<>();
        List<GrantedAuthority> authorities = (List<GrantedAuthority>)(SecurityContextHolder.getContext().getAuthentication().getAuthorities());
        for (GrantedAuthority authority : authorities) {
            results.add(authority.getAuthority());
        }
        return results;
    }
}
