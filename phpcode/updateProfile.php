<?php

	
		
		require "connectdb.php";
		
	
	
	
	
	
	
		
		$firstname = $_POST["firstname"];
		$lastname = $_POST["lastname"];
		$username = $_POST["username"];
		$email = $_POST["email"];
		$password = $_POST["password"];
		
		$firstName = "Emil";
		$lastname = "Emil";
		$username = "Eda";
		$email = "Emil@edada.se";
		$password = "Emil";
		
		
		
		
		$isValidEmail = filter_var($email,FILTER_VALIDATE_EMAIL);

		if($isValidEmail === false && $email != null){
			echo "This Email is not valid";
		}
		else{
			
			$sqlCheckEmail = "Select * FROM `user` WHERE `email` = '$email'";
			$emailQuery = mysqli_query($connect,$sqlCheckEmail);
			
			if(mysqli_num_rows($emailQuery ) > 0){
				echo "Email already exist, try another one";
			}
		
			else{
				if($firstname != null && $lastname != null && $email != null && $password != null){
					
					$sql_updateUser = "UPDATE `user` SET `firstName`='$firstname',`lastName`='$lastname',`email`='$email', `password`= '$password' WHERE `username` LIKE '$username'";
					getEcho("all");
					echo "Test";
				}
				else{
					if($firstname != null){
					$sql_updateUser = "UPDATE `user` SET `firstName`='$firstname',  WHERE `username` LIKE '$username'";
					getEcho("firstname");
					}
					if($lastname != null){
					$sql_updateUser = "UPDATE `user` SET `lastName`='$lastname' WHERE `username` LIKE '$username'";
					getEcho("lastName`");
					}
					if($email != null){
					$sql_updateUser = "UPDATE `user` SET `email`='$email' WHERE `username` LIKE '$username'";
					getEcho("email");
					}
					if($password != null){
					$sql_updateUser = "UPDATE `user` SET `password`= '$password' WHERE `username` LIKE '$username'";
					getEcho("password");
					}
				
				
				}
		
			}
		}
		
		function getEcho($echo){
			
			require "connectdb.php";
			if(mysqli_query($connect,$sql_updateUser)){
				echo "Successfully updated" . $echo . " in your account";
				}
				else{
				echo "Failed to update account";
				}
		}
		
		
	
?>