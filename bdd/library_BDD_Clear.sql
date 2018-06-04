
--- ===== Purge des tables
DELETE FROM t_topo;
DELETE FROM t_site;
DELETE FROM t_secteur;
DELETE FROM t_voie;
DELETE FROM t_longueur;
DELETE FROM t_utilisateur;
DELETE FROM t_location_topo;
DELETE FROM t_commentaire;

COMMIT;