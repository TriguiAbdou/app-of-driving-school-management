-- phpMyAdmin SQL Dump
-- version 4.1.14
-- http://www.phpmyadmin.net
--
-- Client :  127.0.0.1
-- Généré le :  Lun 25 Décembre 2017 à 14:57
-- Version du serveur :  5.6.17
-- Version de PHP :  5.5.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Base de données :  `karhabty1`
--

-- --------------------------------------------------------

--
-- Structure de la table `agence`
--

CREATE TABLE IF NOT EXISTS `agence` (
  `id_agence` int(11) NOT NULL AUTO_INCREMENT,
  `nom_agence` varchar(30) NOT NULL,
  `telephone_agence` int(20) NOT NULL,
  `type_agence` varchar(20) DEFAULT NULL,
  `horaire_travail` varchar(20) NOT NULL,
  `photo_agence` text,
  `id_manager` int(11) DEFAULT NULL,
  `piece_justificative` text NOT NULL,
  `rue` varchar(200) NOT NULL,
  `code_postal` int(11) NOT NULL,
  `ville` varchar(200) NOT NULL,
  `status` varchar(200) NOT NULL,
  `approved` tinyint(4) DEFAULT NULL,
  `latitude` double DEFAULT NULL,
  `longitude` double DEFAULT NULL,
  PRIMARY KEY (`id_agence`),
  KEY `id_manager` (`id_manager`),
  KEY `id_agence` (`id_agence`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=97 ;

--
-- Contenu de la table `agence`
--

INSERT INTO `agence` (`id_agence`, `nom_agence`, `telephone_agence`, `type_agence`, `horaire_travail`, `photo_agence`, `id_manager`, `piece_justificative`, `rue`, `code_postal`, `ville`, `status`, `approved`, `latitude`, `longitude`) VALUES
(91, 'medautoecole', 20176630, NULL, '07:00-20:00', NULL, NULL, 'batinde', 'ibn ibn zoher', 2080, 'Tunis', 'En attente', NULL, NULL, NULL),
(92, 'khaledAUTO-ecole', 22503619, NULL, '08:00-21:00', NULL, NULL, 'batinde', 'mustpha hjaiej', 2036, 'Ariana', 'Rejetée', 0, NULL, NULL),
(93, 'hamadiAuto-ecole', 53530242, NULL, '09:00-19:00', NULL, NULL, '25978418', '17,badis ladghem', 2034, 'Sfax', 'En attente', NULL, NULL, NULL),
(94, 'helmiAUTOecole', 71859685, NULL, '07:00-19:00', NULL, NULL, 'batinde', '21,rue de l''independance', 2080, 'Bizerte', 'Rejetée', 0, NULL, NULL),
(95, 'autoecolekhaled', 20176630, NULL, '07:00-19:00', NULL, NULL, 'batinde', 'ibn sana el molk', 2036, 'Tunis', 'Rejetée', 0, NULL, NULL),
(96, 'abdouautoecole', 20176630, NULL, '07:00-20:00', NULL, NULL, 'Batinde', 'borjlouzir', 2080, 'Tunis', 'Rejetée', 0, NULL, NULL);

-- --------------------------------------------------------

--
-- Structure de la table `agent`
--

CREATE TABLE IF NOT EXISTS `agent` (
  `id_agent` int(11) NOT NULL AUTO_INCREMENT,
  `id_agence` int(11) NOT NULL,
  `nom` varchar(10) NOT NULL,
  `prenom` varchar(10) NOT NULL,
  `sexe` varchar(10) NOT NULL,
  `telephone` int(11) NOT NULL,
  `type` varchar(20) NOT NULL,
  PRIMARY KEY (`id_agent`),
  KEY `id_agence` (`id_agence`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=17 ;

--
-- Contenu de la table `agent`
--

INSERT INTO `agent` (`id_agent`, `id_agence`, `nom`, `prenom`, `sexe`, `telephone`, `type`) VALUES
(15, 92, 'ahlem', 'ali', 'Femme', 20176630, 'Superviseur'),
(16, 94, 'mohamed', 'trigui', 'Homme', 22503619, 'Moniteur');

-- --------------------------------------------------------

--
-- Structure de la table `annonce`
--

CREATE TABLE IF NOT EXISTS `annonce` (
  `id_annonce` int(20) NOT NULL AUTO_INCREMENT,
  `prix_annonce` float NOT NULL,
  `description_annonce` text,
  `date_debut` date DEFAULT NULL,
  `date_fin` date DEFAULT NULL,
  `expirer` tinyint(1) DEFAULT NULL,
  `contacte` int(8) DEFAULT NULL,
  `id_client` int(11) NOT NULL,
  `matricule` varchar(20) NOT NULL,
  PRIMARY KEY (`id_annonce`),
  KEY `id_client` (`id_client`),
  KEY `id_client_2` (`id_client`),
  KEY `matricule` (`matricule`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Structure de la table `chauffeur`
--

CREATE TABLE IF NOT EXISTS `chauffeur` (
  `id_chauffeur` int(11) NOT NULL AUTO_INCREMENT,
  `id_voiture` varchar(11) DEFAULT NULL,
  `nom_chauffeur` varchar(50) NOT NULL,
  `age_chauffeur` int(11) NOT NULL,
  `id_agence` int(11) NOT NULL,
  `prix_chauffeur` float NOT NULL,
  PRIMARY KEY (`id_chauffeur`),
  KEY `id_voiture` (`id_voiture`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Structure de la table `commentaire`
--

CREATE TABLE IF NOT EXISTS `commentaire` (
  `id_commentaire` int(11) NOT NULL AUTO_INCREMENT,
  `id_client` int(11) NOT NULL,
  `id_agence` int(11) DEFAULT NULL,
  `description` text NOT NULL,
  `id_event` int(11) NOT NULL,
  PRIMARY KEY (`id_commentaire`),
  KEY `id_client` (`id_client`),
  KEY `id_agence` (`id_agence`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Structure de la table `events`
--

CREATE TABLE IF NOT EXISTS `events` (
  `id_event` int(10) NOT NULL AUTO_INCREMENT,
  `date_debut` date NOT NULL,
  `lieu` varchar(50) NOT NULL,
  `sujet` varchar(50) NOT NULL,
  `frais_inscription` float unsigned NOT NULL,
  `description` text NOT NULL,
  `approuved` tinyint(1) NOT NULL,
  `id_user` int(11) NOT NULL,
  `nom_event` varchar(50) NOT NULL,
  `date_fin` date NOT NULL,
  `image` varchar(500) DEFAULT NULL,
  `nombre_de_place` int(11) NOT NULL,
  PRIMARY KEY (`id_event`),
  KEY `id_user` (`id_user`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Structure de la table `exercice`
--

CREATE TABLE IF NOT EXISTS `exercice` (
  `id_exercice` int(11) NOT NULL AUTO_INCREMENT,
  `id_agent` int(11) NOT NULL,
  `id_client` int(11) NOT NULL,
  `matricule` varchar(20) NOT NULL,
  `type` varchar(20) NOT NULL,
  `prix` float NOT NULL,
  `date_heure` varchar(50) NOT NULL,
  PRIMARY KEY (`id_exercice`),
  KEY `id_agent` (`id_agent`),
  KEY `id_client` (`id_client`),
  KEY `matricule` (`matricule`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=23 ;

--
-- Contenu de la table `exercice`
--

INSERT INTO `exercice` (`id_exercice`, `id_agent`, `id_client`, `matricule`, `type`, `prix`, `date_heure`) VALUES
(21, 15, 7468678, '117 TU 1524', 'Conduite', 200, 'Le 18/02/2017 à 22:00'),
(22, 16, 7468678, '21 TU 2145', 'Conduite', 18.5, 'Le 18/03/2016 à 20:00');

-- --------------------------------------------------------

--
-- Structure de la table `forum`
--

CREATE TABLE IF NOT EXISTS `forum` (
  `id_publication` int(11) NOT NULL AUTO_INCREMENT,
  `id_client` int(11) NOT NULL,
  `sujet_publication` varchar(50) NOT NULL,
  `message` text NOT NULL,
  PRIMARY KEY (`id_publication`),
  KEY `id_client` (`id_client`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Structure de la table `location`
--

CREATE TABLE IF NOT EXISTS `location` (
  `id_client` int(10) NOT NULL,
  `id_agence` int(10) NOT NULL,
  `matricule` varchar(30) NOT NULL,
  `date_debut` date NOT NULL,
  `date_fin` date NOT NULL,
  `prix_location` float NOT NULL,
  `chauffeur` tinyint(1) DEFAULT NULL,
  `approuved` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id_client`,`id_agence`,`matricule`),
  KEY `id_agence` (`id_agence`),
  KEY `matricule` (`matricule`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `offre_location`
--

CREATE TABLE IF NOT EXISTS `offre_location` (
  `id_offre` int(11) NOT NULL AUTO_INCREMENT,
  `nom_offre` varchar(255) NOT NULL,
  `prix_offre` float NOT NULL,
  `description_offre` text NOT NULL,
  `id_voiture` varchar(255) NOT NULL,
  `id_chauffeur` int(11) NOT NULL,
  `image` text NOT NULL,
  PRIMARY KEY (`id_offre`),
  KEY `id_voiture` (`id_voiture`),
  KEY `id_chauffeur` (`id_chauffeur`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Structure de la table `photo_voiture`
--

CREATE TABLE IF NOT EXISTS `photo_voiture` (
  `id_photo` int(11) NOT NULL AUTO_INCREMENT,
  `id_voiture` varchar(11) NOT NULL,
  `image` text NOT NULL,
  PRIMARY KEY (`id_photo`),
  KEY `id_voiture` (`id_voiture`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Structure de la table `reservation_service`
--

CREATE TABLE IF NOT EXISTS `reservation_service` (
  `id_reservation` int(11) NOT NULL AUTO_INCREMENT,
  `id` int(8) NOT NULL,
  `id_service` int(10) NOT NULL,
  `date_reservation` varchar(20) NOT NULL,
  `approuve` varchar(20) NOT NULL,
  PRIMARY KEY (`id_reservation`),
  KEY `id` (`id`),
  KEY `id_service` (`id_service`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Structure de la table `services`
--

CREATE TABLE IF NOT EXISTS `services` (
  `id_service` int(10) NOT NULL AUTO_INCREMENT,
  `id_agence` int(11) NOT NULL,
  `nom_service` varchar(80) NOT NULL,
  `prix_service` varchar(10) NOT NULL,
  PRIMARY KEY (`id_service`),
  KEY `id_agence` (`id_agence`),
  KEY `id_service` (`id_service`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Structure de la table `souscommentaire`
--

CREATE TABLE IF NOT EXISTS `souscommentaire` (
  `id_sousCommentaire` int(11) NOT NULL AUTO_INCREMENT,
  `idCommenatire` int(11) DEFAULT NULL,
  `id_client` int(11) NOT NULL,
  `commentaire` varchar(50000) NOT NULL,
  `dateCommentaire` timestamp NOT NULL,
  `Aime` int(11) DEFAULT NULL,
  PRIMARY KEY (`id_sousCommentaire`),
  KEY `idCommenatire` (`idCommenatire`),
  KEY `id_client` (`id_client`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Structure de la table `user`
--

CREATE TABLE IF NOT EXISTS `user` (
  `id` int(8) NOT NULL,
  `nom` varchar(20) NOT NULL,
  `prenom` varchar(20) NOT NULL,
  `password` varchar(20) NOT NULL,
  `date_naissance` date NOT NULL,
  `telephone` int(11) NOT NULL,
  `mail` varchar(30) NOT NULL,
  `adresse` varchar(50) NOT NULL,
  `approuved` tinyint(1) DEFAULT NULL,
  `banned` tinyint(1) NOT NULL DEFAULT '0',
  `Role` varchar(100) NOT NULL,
  `image` text,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table `user`
--

INSERT INTO `user` (`id`, `nom`, `prenom`, `password`, `date_naissance`, `telephone`, `mail`, `adresse`, `approuved`, `banned`, `Role`, `image`) VALUES
(7468678, 'abdou', 'trigui', '222', '2017-03-14', 20176630, 'ttt', 'aaa', 1, 0, 'aa', 'aa'),
(11111111, 'mhamed', 'hattab', '123456', '1902-03-02', 93112189, 'mha@mail.com', 'ariana', 1, 0, 'ROLE_CLIENT', 'imagee'),
(11861810, 'Jhinaoui', 'Amira', '11861810', '1991-04-24', 99612818, 'amira.jhinaoui@esprit.tn', 'esprit ghazela', 0, 0, 'admin', '');

-- --------------------------------------------------------

--
-- Structure de la table `voiture`
--

CREATE TABLE IF NOT EXISTS `voiture` (
  `matricule` varchar(50) NOT NULL,
  `marque` varchar(50) NOT NULL,
  `couleur` varchar(50) NOT NULL,
  `carburant` varchar(20) NOT NULL,
  `age` int(11) NOT NULL,
  `kilometrage` int(10) unsigned DEFAULT NULL,
  `puissance` int(10) unsigned DEFAULT NULL,
  `disponible` tinyint(1) DEFAULT NULL,
  `id_user` int(11) DEFAULT NULL,
  `carrousserie` varchar(20) DEFAULT NULL,
  `boite` varchar(20) DEFAULT NULL,
  `gps` tinyint(1) DEFAULT NULL,
  `climatisation` tinyint(1) DEFAULT NULL,
  `airbag` tinyint(1) DEFAULT NULL,
  `nbr_porte` int(10) unsigned DEFAULT NULL,
  `frein_abs` tinyint(1) DEFAULT NULL,
  `alarme` tinyint(1) DEFAULT NULL,
  `id_agence` int(11) NOT NULL,
  `prix_location` float DEFAULT NULL,
  PRIMARY KEY (`matricule`),
  KEY `id_user` (`id_user`),
  KEY `id_agence` (`id_agence`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table `voiture`
--

INSERT INTO `voiture` (`matricule`, `marque`, `couleur`, `carburant`, `age`, `kilometrage`, `puissance`, `disponible`, `id_user`, `carrousserie`, `boite`, `gps`, `climatisation`, `airbag`, `nbr_porte`, `frein_abs`, `alarme`, `id_agence`, `prix_location`) VALUES
('117 TU 1524', 'ford', 'bleu', 'Gazole', 2016, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 93, NULL),
('120 TU 2001', 'Ford', 'rouge', 'Essence', 2001, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 92, NULL),
('17 TU 4152', 'kia', 'rouge', 'Gazole', 2001, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 91, NULL),
('192 TU 1745', 'KIA', 'noire', 'Essence', 1995, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 91, NULL),
('21 TU 2145', 'ford', 'rouge', 'Essence', 2001, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 94, NULL),
('21 TU 255', 'ford', 'np', 'Essence', 2005, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 94, NULL);

--
-- Contraintes pour les tables exportées
--

--
-- Contraintes pour la table `agence`
--
ALTER TABLE `agence`
  ADD CONSTRAINT `agence_ibfk_1` FOREIGN KEY (`id_manager`) REFERENCES `user` (`id`);

--
-- Contraintes pour la table `agent`
--
ALTER TABLE `agent`
  ADD CONSTRAINT `agent_ibfk_1` FOREIGN KEY (`id_agence`) REFERENCES `agence` (`id_agence`);

--
-- Contraintes pour la table `annonce`
--
ALTER TABLE `annonce`
  ADD CONSTRAINT `annonce_ibfk_1` FOREIGN KEY (`id_client`) REFERENCES `user` (`id`),
  ADD CONSTRAINT `annonce_ibfk_2` FOREIGN KEY (`matricule`) REFERENCES `voiture` (`matricule`);

--
-- Contraintes pour la table `chauffeur`
--
ALTER TABLE `chauffeur`
  ADD CONSTRAINT `chauffeur_ibfk_1` FOREIGN KEY (`id_voiture`) REFERENCES `voiture` (`matricule`);

--
-- Contraintes pour la table `commentaire`
--
ALTER TABLE `commentaire`
  ADD CONSTRAINT `commentaire_ibfk_1` FOREIGN KEY (`id_client`) REFERENCES `user` (`id`),
  ADD CONSTRAINT `commentaire_ibfk_2` FOREIGN KEY (`id_agence`) REFERENCES `agence` (`id_agence`);

--
-- Contraintes pour la table `events`
--
ALTER TABLE `events`
  ADD CONSTRAINT `events_ibfk_1` FOREIGN KEY (`id_user`) REFERENCES `user` (`id`);

--
-- Contraintes pour la table `exercice`
--
ALTER TABLE `exercice`
  ADD CONSTRAINT `exercice_ibfk_1` FOREIGN KEY (`id_agent`) REFERENCES `agent` (`id_agent`),
  ADD CONSTRAINT `exercice_ibfk_2` FOREIGN KEY (`id_client`) REFERENCES `user` (`id`),
  ADD CONSTRAINT `exercice_ibfk_3` FOREIGN KEY (`matricule`) REFERENCES `voiture` (`matricule`);

--
-- Contraintes pour la table `forum`
--
ALTER TABLE `forum`
  ADD CONSTRAINT `forum_ibfk_1` FOREIGN KEY (`id_client`) REFERENCES `user` (`id`);

--
-- Contraintes pour la table `location`
--
ALTER TABLE `location`
  ADD CONSTRAINT `location_ibfk_1` FOREIGN KEY (`id_agence`) REFERENCES `agence` (`id_agence`),
  ADD CONSTRAINT `location_ibfk_2` FOREIGN KEY (`matricule`) REFERENCES `voiture` (`matricule`);

--
-- Contraintes pour la table `offre_location`
--
ALTER TABLE `offre_location`
  ADD CONSTRAINT `offre_location_ibfk_1` FOREIGN KEY (`id_voiture`) REFERENCES `voiture` (`matricule`),
  ADD CONSTRAINT `offre_location_ibfk_2` FOREIGN KEY (`id_chauffeur`) REFERENCES `chauffeur` (`id_chauffeur`);

--
-- Contraintes pour la table `photo_voiture`
--
ALTER TABLE `photo_voiture`
  ADD CONSTRAINT `photo_voiture_ibfk_1` FOREIGN KEY (`id_voiture`) REFERENCES `voiture` (`matricule`);

--
-- Contraintes pour la table `reservation_service`
--
ALTER TABLE `reservation_service`
  ADD CONSTRAINT `reservation_service_ibfk_1` FOREIGN KEY (`id_service`) REFERENCES `services` (`id_service`),
  ADD CONSTRAINT `reservation_service_ibfk_2` FOREIGN KEY (`id`) REFERENCES `user` (`id`);

--
-- Contraintes pour la table `services`
--
ALTER TABLE `services`
  ADD CONSTRAINT `services_ibfk_1` FOREIGN KEY (`id_agence`) REFERENCES `agence` (`id_agence`);

--
-- Contraintes pour la table `souscommentaire`
--
ALTER TABLE `souscommentaire`
  ADD CONSTRAINT `souscommentaire_ibfk_1` FOREIGN KEY (`idCommenatire`) REFERENCES `commentaire` (`id_commentaire`),
  ADD CONSTRAINT `souscommentaire_ibfk_2` FOREIGN KEY (`id_client`) REFERENCES `user` (`id`);

--
-- Contraintes pour la table `voiture`
--
ALTER TABLE `voiture`
  ADD CONSTRAINT `voiture_ibfk_1` FOREIGN KEY (`id_user`) REFERENCES `user` (`id`),
  ADD CONSTRAINT `voiture_ibfk_2` FOREIGN KEY (`id_agence`) REFERENCES `agence` (`id_agence`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
