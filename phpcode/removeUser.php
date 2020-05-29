<?php

	
		
		require "connectdb.php";
		
	
	
		$username = $_POST["username"];
		
		//$username = "T";
		
		
		
		
	
		
		
	
			$sqlCheckIfExist = "DELETE FROM `user` WHERE `username` LIKE '$username'";
			if(mysqli_query($connect,$sqlCheckIfExist)){
                    
                echo "The user has been deleted";
					
			
			
			}
			else{
					echo "The user was not deleted";
			}
			
			
			
			
		
			
		
		
	
?>