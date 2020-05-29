<?php

	
		
	if (class_exists('PDO')) {
		echo "<p>PDO exists and the following PDO drivers are loaded.<pre>";
		print_r(PDO::getAvailableDrivers());
	}

	if (in_array("mysql", PDO::getAvailableDrivers())) {
		echo "<p style='color:green'>mysql PDO driver IS enabled";
	} 
	else {
    echo "<p style='color:red'>mysql PDO driver IS NOT enabled";
	}
		
	
?>