<?php

	
		
		require "connectdb.php";
		
		
	
		
		$username = $_POST["username"];
		$password = $_POST["password"];
		
		//$username = "Emil123";
		//$password = "Abcd1234";
		
		
		
		if($connect){
			

			
				$sqlCheckUsername = "Select * FROM `user` WHERE `username` = '$username'";
				$usernameQuery = mysqli_query($connect,$sqlCheckUsername);
				
				
				if(mysqli_num_rows($usernameQuery) > 0){
					$sqlLogin = "Select `userType` FROM `user` WHERE `username` LIKE '$username' AND `password` LIKE '$password'";
					$sqlLoginQuery = mysqli_query($connect,$sqlLogin);
					if($sqlLoginQuery->num_rows > 0){
						
					
						header("Content-Type: text/plain");
						while($row = $sqlLoginQuery->fetch_assoc()) {
							echo "Login Success,".$row["userType"];
					}
					}
					else{
						echo "Wrong password";
					}
				}
				else{
					echo "This username is not registered";
					
				}
		
			
		}
		else{
			echo "Connection Error";
		}
		
	
?>