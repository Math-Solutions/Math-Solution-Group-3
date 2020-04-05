<?php

	
		
		require "connectdb.php";
		
		
	

		
		
		
		
		
		if($connect){
			

			
				$sqlCheckCourse= "Select `courseID`, `courseArea` FROM `course` WHERE `courseArea` = 'Math'";
				$courseQuery = mysqli_query($connect,$sqlCheckCourse);
				if ($courseQuery->num_rows > 0) {
					// output data of each row
					while($row = $courseQuery->fetch_assoc()) {
						echo "courseID: " . $row["courseID"]."";
						
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