<?php
    // Fonction.php
    function multiplie(int $taille = 5){
        echo "<h2>Table de multiplication</h2>";
        echo "<table border ='2'>";
        echo "<tr><th>X</th>";
        for($i = 1; $i <= $taille;$i++){
            echo "<th>$i</th>";
        }
        echo "</tr>";
        for($i = 1; $i <$taille+1; $i++){
            echo "<tr><th>$i</th>";
            for($j = 1; $j<$taille+1; $j++){
                echo "<td>".$i*$j."</td>";
            }
            echo "</tr>";
			
        }
		echo "</table>";
    }
?>
