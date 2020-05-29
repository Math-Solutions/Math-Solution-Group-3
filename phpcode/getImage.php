<?php

	
		
		require "connectdb.php";
		
	
	
	
	
	
	
	
		
	
		$name = $_POST["name"];
		
		//$name = "Name";
		
		
		
		
	
		
		
	
			$sqlCheckIfExist = "Select * FROM `solution` WHERE `name` LIKE '$name'";
			if(mysqli_query($connect,$sqlCheckIfExist)){
				$query = mysqli_query($connect,$sqlCheckIfExist);
				if ($query->num_rows > 0) {
					
			
					header("Content-Type: text/plain");
                    while($row = $query->fetch_assoc()) {
                        echo $row["image_path"]. "," .$row["comment"]. "," . $row["username"];
					}
					
					
			
		
		
			
				}
				else{
					echo "No image found";
				}
				
			}
			
			
			
		
			
		
		
	
?>