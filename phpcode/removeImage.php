<?php

	
		
		require "connectdb.php";
		
	
	
	
	
	
	
	
		
	
		$name = $_POST["name"];
		
		//$name = "Studetn1";
		
		
		
		
	
		
		
	
			$sqlCheckIfExist = "DELETE FROM `solution` WHERE `name` LIKE '$name'";
			if(mysqli_query($connect,$sqlCheckIfExist)){
                    
                echo "The picture has been deleted";
					
			
			
			}
			else{
					echo "The picture was not deleted";
			}
			
			
			
			
		
			
		
		
	
?>