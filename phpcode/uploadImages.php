<?php

	
		
		require "connectdb.php";
		
	
	
	
	
	
	
	/*
		$tag2 = $_POST["tag2"];
		$tag3 = $_POST["tag3"];
		
				
		$taskID = "1.2";
		$username = "EMil123";
	    $comment =" Fuck test";
		$bookID = 		"Analys1"; 		
		
		*/
		$image = $_POST["image"];
		$name = $_POST["name"];
		$username = $_POST["username"];
		$comment = $_POST["comment"];
		$taskID = $_POST["taskID"];
		$bookID = $_POST["bookID"];
		
		
		
		$upload_path = "mathSolutionImages/$name.jpg";
		//s$tagArray = array('$_POST["tag1"]','$_POST["tag2"'],'$_POST["tag3"'],'$_POST["tag4"'],'$_POST["tag5"']);
		$tagArray = array("1","2","3","4","5");
		
		
		
	
			$sqlCheckIfExist = "Select * FROM `solution` WHERE `name` LIKE '$name'";
			if(mysqli_query($connect,$sqlCheckIfExist)){
				$query = mysqli_query($connect,$sqlCheckIfExist);
				if ($query->num_rows > 0) {
					echo "This image name already exist";
			
				}
				else{
				//$sql_insertPic = "INSERT INTO `solution`(`image`, `name`, `img_dir`, `date`, `username`, `comment`, `taskID`,`bookID`) VALUES ('$image','$name','$upload_path',NOW(),'$username','$comment','$taskID','$bookID')";
					$sql_insertPic = "INSERT INTO `solution`(`name`, `image_path`, `date`, `username`, `comment`, `taskID`,`bookID`) VALUES ('$name','$upload_path',NOW(),'$username','$comment','$taskID','$bookID')";
			
					if($insertPicQuery = mysqli_query($connect,$sql_insertPic)){
					file_put_contents($upload_path,base64_decode($image));;
					echo "The Solution was uploaded successfully";
					}
					else{
						echo "The Solution was not uploaded successfully";
					}
					
			
		
		
			
				for($i =0 ;$i<count($tagArray); $i++){
				//insertTags($tagArray[$i],$task);
				}
				}
			}
			
			
			
		
			
		
		function insertTags($tag,$task){
			
			require "connectdb.php";
			if(is_null($tag) or is_null($task)){
			echo "is empty";
			}
			else{
				$sql_insertTags = "INSERT INTO solutions (tag, taskID) VALUES('$tag','$task')";
				$inserttagsQuery = mysqli_query($connect,$sql_insertTags);
				echo "tags where successfully insterted into the database";
			}
		}
	
?>