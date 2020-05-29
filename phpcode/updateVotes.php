<?php

	
		
		require "connectdb.php";
		
	
	
	
	
	
	
	
		
	
		$name = $_POST["name"];
		$votes = $_POST["votes"];
		
		//$name = "name";
		//$votes = "12";
		
		
	
		
		
	
			$sqlCheckIfExist = "UPDATE `solution` SET `totalVotes`='$votes' WHERE `name` LIKE '$name'";
			if(mysqli_query($connect,$sqlCheckIfExist)){
				echo "Votes have been changed";
				
				
			}
			else{
				echo "you did not change anything";
			}
			
			
			
		
			
		
		
	
?>