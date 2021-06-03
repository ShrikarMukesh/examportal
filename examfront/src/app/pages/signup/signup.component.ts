import { Component, OnInit } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { UserService } from 'src/app/services/user.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css']
})
export class SignupComponent implements OnInit {

  constructor(private userService:UserService,private _snack:MatSnackBar) { }

  public user={
    username:'',
    password:'',
    firstName:'',
    lastName:'',
    email:'',
    phone:'',
  };

  ngOnInit(): void {}
    
    formSubmit(){ 
      console.log(this.user);
      if(this.user.username=='' || this.user.username == null){
         this._snack.open("Username is required!!",'',{
           duration:3000,
         });
         return;
      
        }
       
      //addUser:userService
      this.userService.addUser(this.user).subscribe(
        
        (data : any )=>{
          
          //sucess
          console.log(data);
          //alert('sucess')
          Swal.fire('Success done !!','user is registered with id '+data.id,'success');

        },
        (error)=>{
            console.log(error); 
            //alert('some thing went wrong');
            this._snack.open('something went wrong!!','',{
              duration:3000,
            });
        }
      );
      console.log("shrikar");
    }

}
