<?php

	
		
		require "connectdb.php";
		
		
		
	
		//$book = $_POST["book"];
		//$chapter = $_POST["chapter"];
		$book = "Analys1";
		$chapter = "1";
		
		if($connect){
			

			
			
							
				$sqlCheckTask= "Select `taskID` FROM `task` WHERE `bookID` = '$book' AND `taskID` LIKE '$chapter%' Order BY 1*SUBSTRING_INDEX(`taskID`, '.', 1) ASC";
                $query = mysqli_query($connect,$sqlCheckTask);
                if ($query->num_rows > 0) {
                    // output data of each row
                    header("Content-Type: text/plain");
                    while($row = $query->fetch_assoc()) {
                        echo $row["taskID"].",";
					}
					
					
				}
				else {
				echo "0 results";
				}
				
			
		}
		else{
			echo "Connection Error";
		}
		
	
?>