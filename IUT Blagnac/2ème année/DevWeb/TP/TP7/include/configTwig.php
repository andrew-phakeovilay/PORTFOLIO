<?php
	// __DIR__ retourne le répertoire courant, ici include
	// mais avec dirname(__DIR__) on récupère le répertoire père du répertoire courant, pour nous TP7
	// et c'est ce qu'on souhaite pour ensuite "descendre" vers le répertoire vendor et vues...
	require dirname(__DIR__).'/vendor/autoload.php';
	// require __DIR__.'/vendor/autoload.php';
	// require __DIR__.'/vendor/autoload.php';
	use Twig\Environment;
	use Twig\Loader\FilesystemLoader;
	$loader = new FilesystemLoader(dirname(__DIR__).'/vues');
	$twig = new Environment($loader);
?>