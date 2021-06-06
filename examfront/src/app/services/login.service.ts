import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import baseurl from './helper';

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  constructor(private http:HttpClient) { }

  //get current user
  public getCurrentUser(){
    return this.http.get(`${baseurl}/current-user`);
  }
  //generate token
  public generateToken(loginData : any){
    return this.http.post(`${baseurl}/generate-token`,loginData);

  }

  //loginUser: Set token in local storage
  public loginUser(token :any){
         localStorage.setItem('token',token);
         return true;
  }

  //isLogin: user is logged in or not
  public isLoggedIn(){
     let tokenStr= localStorage.getItem("token")
     if(tokenStr == undefined || tokenStr == '' || tokenStr==null){
          return false;

     }
     else{

       return true;
     }
  }

  //logout: remove 
  public logout(){
    localStorage.removeItem('token');
    localStorage.removeItem('user');
    return true;
  }

  //get token
  public getToken(){
    return localStorage.getItem('token');
  }

  //setUserDetails
  public setUser(user : any){
     localStorage.setItem('user',JSON.stringify(user));
  }
  //getUser
  public getUser(){
     let userStr =  localStorage.getItem("user");

     if(userStr != null)
     {
          return JSON.parse(userStr);
     }
     else
     {
        this.logout();
        return null;
 
     }
    
  }
  //get user role
  public getUserRole(){
    let user = this.getUser();
    return user.authorities[0].authority;
  }

}
