<?php

	
		
		require "connectdb.php";
		
	
	
	
	
	
	
	
		
	
		$username = $_POST["username"];
		$userType = $_POST["userType"];
		
		//$username = "tr";
		//$userType = "Teacher";
		
		
	
		
		
	
			$sqlCheckIfExist = "UPDATE `user` SET `userType`='$userType' WHERE `username` LIKE '$username'";
			if(mysqli_query($connect,$sqlCheckIfExist)){
				echo "Users access have been changed";
				
				
			}
			else{
				echo "you did not change anything";
			}
			
			
			
		
			
		
		
	
?>