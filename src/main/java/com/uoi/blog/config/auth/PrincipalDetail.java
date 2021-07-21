package com.uoi.blog.config.auth;

import com.uoi.blog.model.User;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;


// 스프링 시큐리티가 로그인 요청을 가로채서 로그인을 진행하고 완료하면 UserDetails 타입의 오브젝트를
// 스프링 시큐리티의 고유한 세션 저장소에 저장을 해준다.
@Getter
public class PrincipalDetail implements UserDetails {
    private User user; //콤포지션 : 객체를 품고있는 것

    public PrincipalDetail(User user){ this.user = user;}

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    } //계정이 만료되지 않았는지 리턴한다. t: 만료안됨, f: 만료됨

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    } //비밀번호가 만료되지 않았는지 리턴한다. t: 만료안됨

    @Override
    public boolean isEnabled() {
        return true;
    } //계정이 활성화인지 리턴 t: 활성화

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        Collection<GrantedAuthority> collectors = new ArrayList<>(); //AL이 Collection의 자식
        collectors.add( () -> { return "ROLE_" + user.getRole(); }); // "ROLE_ 을 꼭 붙일 것
        return collectors;
    } //계정이 무슨 권한을 가지는지 리턴, 타입이 특이함

}
