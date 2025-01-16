-- phpMyAdmin SQL Dump
-- version 4.6.6deb5
-- https://www.phpmyadmin.net/
--
-- Client :  localhost:3306
-- Généré le :  Ven 14 Août 2020 à 01:52
-- Version du serveur :  5.7.31-0ubuntu0.18.04.1
-- Version de PHP :  7.2.24-0ubuntu0.18.04.6

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données :  `mairie`
--

-- --------------------------------------------------------

--
-- Structure de la table `acte_de_mariage`
--

CREATE TABLE `acte_de_mariage` (
  `id_acte_mar` int(11) NOT NULL,
  `date_mar` date NOT NULL,
  `lieu_mariage` varchar(100) NOT NULL,
  `id_extrait_conjoint` int(11) NOT NULL,
  `id_extrait_conjointe` int(11) NOT NULL,
  `date_enregistrement` date NOT NULL,
  `id_agent` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table `acte_de_mariage`
--

INSERT INTO `acte_de_mariage` (`id_acte_mar`, `date_mar`, `lieu_mariage`, `id_extrait_conjoint`, `id_extrait_conjointe`, `date_enregistrement`, `id_agent`) VALUES
(1, '2020-08-14', 'cocody', 1, 12, '2020-08-14', 1),
(2, '2020-08-23', 'ABIDJAN', 1, 12, '2020-08-14', 1);

-- --------------------------------------------------------

--
-- Structure de la table `agent`
--

CREATE TABLE `agent` (
  `id_agent` int(11) NOT NULL,
  `nom` varchar(50) NOT NULL,
  `prenoms` varchar(225) NOT NULL,
  `email` text NOT NULL,
  `user_name` varchar(100) NOT NULL,
  `mot_d_passe` varchar(100) NOT NULL,
  `id_mairie` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table `agent`
--

INSERT INTO `agent` (`id_agent`, `nom`, `prenoms`, `email`, `user_name`, `mot_d_passe`, `id_mairie`) VALUES
(1, 'Papitou ', 'Wazabenga Epipinapi', 'papitou@gmail.com', 'papitou15', 'papitou@mairiepassword99', 1);

-- --------------------------------------------------------

--
-- Structure de la table `individus`
--

CREATE TABLE `individus` (
  `id_extrait` int(11) NOT NULL,
  `nom` varchar(100) NOT NULL,
  `prenoms` varchar(100) NOT NULL,
  `sexe` varchar(20) NOT NULL,
  `date_naiss` date NOT NULL,
  `heure_naiss` varchar(50) NOT NULL,
  `lieu_naiss` varchar(100) NOT NULL,
  `nom_pere` varchar(100) NOT NULL,
  `domicile_pere` varchar(100) NOT NULL,
  `profession_pere` varchar(50) NOT NULL,
  `nom_mere` varchar(100) NOT NULL,
  `domicile_mere` varchar(100) NOT NULL,
  `profession_mere` varchar(50) NOT NULL,
  `date_enregistrem` date NOT NULL,
  `numero_enregistrem` int(100) NOT NULL,
  `id_mairie` int(11) NOT NULL,
  `id_agent` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table `individus`
--

INSERT INTO `individus` (`id_extrait`, `nom`, `prenoms`, `sexe`, `date_naiss`, `heure_naiss`, `lieu_naiss`, `nom_pere`, `domicile_pere`, `profession_pere`, `nom_mere`, `domicile_mere`, `profession_mere`, `date_enregistrem`, `numero_enregistrem`, `id_mairie`, `id_agent`) VALUES
(1, 'Ablbl', 'LDBQSDBQB', 'Masculin', '2020-08-12', '3:5', 'bkjdqsd', 'apap', 'ydc', 'INgenier', 'meme', 'gvhbjn', 'Sage femme', '2020-08-12', 1, 1, 1),
(12, 'ChaoMao', 'AZrtyuiop', 'Femme', '2020-08-12', '1:4', 'gcjvhkblnm,l', 'xdfchgvjbknl', 'gvuhbjknl,ml;ù', 'gvjhbknl,ml', 'q(srdtfuygihojp', 'xtfycgvuhbjnlk,m', 'cgvjhbknl,', '2020-08-13', 1, 1, 1);

-- --------------------------------------------------------

--
-- Structure de la table `mairie`
--

CREATE TABLE `mairie` (
  `id_mairie` int(11) NOT NULL,
  `communes` varchar(65) NOT NULL,
  `adresse` varchar(80) NOT NULL,
  `nom_officier` varchar(200) DEFAULT NULL,
  `signature` text
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table `mairie`
--

INSERT INTO `mairie` (`id_mairie`, `communes`, `adresse`, `nom_officier`, `signature`) VALUES
(1, 'Cocody', 'BPV 128 Abidjan', 'YAO K. Matthieu', 'YAO K. Matthieu');

-- --------------------------------------------------------

--
-- Structure de la table `operation`
--

CREATE TABLE `operation` (
  `id_operation` int(11) NOT NULL,
  `date_operation` date NOT NULL,
  `libelle-operation` varchar(50) NOT NULL,
  `id_agent` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Index pour les tables exportées
--

--
-- Index pour la table `acte_de_mariage`
--
ALTER TABLE `acte_de_mariage`
  ADD PRIMARY KEY (`id_acte_mar`),
  ADD KEY `id_agent_am___fk` (`id_agent`);

--
-- Index pour la table `agent`
--
ALTER TABLE `agent`
  ADD PRIMARY KEY (`id_agent`),
  ADD KEY `FK_id_mairie` (`id_mairie`);

--
-- Index pour la table `individus`
--
ALTER TABLE `individus`
  ADD PRIMARY KEY (`id_extrait`),
  ADD KEY `id_agent_fk` (`id_agent`),
  ADD KEY `id_mairie_fk` (`id_mairie`);

--
-- Index pour la table `mairie`
--
ALTER TABLE `mairie`
  ADD PRIMARY KEY (`id_mairie`);

--
-- Index pour la table `operation`
--
ALTER TABLE `operation`
  ADD PRIMARY KEY (`id_operation`),
  ADD KEY `id_operation_fk` (`id_agent`);

--
-- AUTO_INCREMENT pour les tables exportées
--

--
-- AUTO_INCREMENT pour la table `acte_de_mariage`
--
ALTER TABLE `acte_de_mariage`
  MODIFY `id_acte_mar` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT pour la table `agent`
--
ALTER TABLE `agent`
  MODIFY `id_agent` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT pour la table `individus`
--
ALTER TABLE `individus`
  MODIFY `id_extrait` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=18;
--
-- AUTO_INCREMENT pour la table `mairie`
--
ALTER TABLE `mairie`
  MODIFY `id_mairie` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT pour la table `operation`
--
ALTER TABLE `operation`
  MODIFY `id_operation` int(11) NOT NULL AUTO_INCREMENT;
--
-- Contraintes pour les tables exportées
--

--
-- Contraintes pour la table `acte_de_mariage`
--
ALTER TABLE `acte_de_mariage`
  ADD CONSTRAINT `id_agent_am___fk` FOREIGN KEY (`id_agent`) REFERENCES `agent` (`id_agent`);

--
-- Contraintes pour la table `agent`
--
ALTER TABLE `agent`
  ADD CONSTRAINT `FK_id_mairie` FOREIGN KEY (`id_mairie`) REFERENCES `mairie` (`id_mairie`);

--
-- Contraintes pour la table `individus`
--
ALTER TABLE `individus`
  ADD CONSTRAINT `id_agent_fk` FOREIGN KEY (`id_agent`) REFERENCES `agent` (`id_agent`) ON UPDATE CASCADE,
  ADD CONSTRAINT `id_mairie_fk` FOREIGN KEY (`id_mairie`) REFERENCES `mairie` (`id_mairie`) ON UPDATE CASCADE;

--
-- Contraintes pour la table `operation`
--
ALTER TABLE `operation`
  ADD CONSTRAINT `id_operation_fk` FOREIGN KEY (`id_agent`) REFERENCES `agent` (`id_agent`) ON DELETE CASCADE ON UPDATE CASCADE;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
