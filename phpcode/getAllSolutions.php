<?php

	
		
		require "connectdb.php";
		
		
		
	
		$book = $_POST["book"];
		$task = $_POST["task"];
		$userType = $_POST["userType"];
		//$book = "Analys1";
		//$task = "1.1";
		//$userType = "Student";
		
		$echoTotVotes = "";
		$echoImgPath = "";
		
		if($connect){
			

			
			if($userType !== 'Teacher'){
				$sqlGetAllSolutions= "SELECT DISTINCT `name`,`totalVotes` FROM `solution` INNER JOIN `user` ON solution.username = user.username  WHERE `bookID` LIKE '$book' AND `taskID` LIKE '$task' AND `userType` NOT LIKE 'Teacher' ORDER BY `totalVotes` DESC LIMIT 5";
				
			}
			else{			
				$sqlGetAllSolutions= "SELECT DISTINCT `name`,`totalVotes` FROM `solution` INNER JOIN `user` ON solution.username = user.username  WHERE `bookID` LIKE '$book' AND `taskID` LIKE '$task' AND `userType` LIKE '$userType' ORDER BY `totalVotes` DESC LIMIT 5";
			}
				$query = mysqli_query($connect,$sqlGetAllSolutions);
				
                if ($query->num_rows > 0) {
                    // output data of each row
                    header("Content-Type: text/plain");
                    while($row = $query->fetch_assoc()) {
                       $echoImgPath = $echoImgPath .  $row["name"]. ",";
					    $echoTotVotes = $echoTotVotes .  $row["totalVotes"]. ",";
					}
					echo  $echoImgPath . "_" . $echoTotVotes;
					
					
					
				}
				else {
					echo "no images found";
				}
				
			
		}
		else{
			echo "Connection Error";
		}
		
	
?>