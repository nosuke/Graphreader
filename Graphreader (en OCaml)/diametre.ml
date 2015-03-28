include Scanf;;
exception Fail of string;;

let chan path = open_in path;;

let rec getGraph graph channel nbArete= if nbArete == 0 then graph else 
	getGraph (sscanf (input_line channel) "%d %d %d" (fun i j k ->(i,j,k))::graph) channel (nbArete - 1);;

let head1 channel =
	input_line channel; 
	sscanf (input_line channel) "%s %d" (fun s i -> i);;

let head2 channel =
	input_line channel;
	input_line channel; 
	sscanf (input_line channel) "%s %d" (fun s i -> i);;

let readGraph channel nbArete  =
	input_line channel;
	input_line channel;
	input_line channel;
	input_line channel;
	getGraph [] channel nbArete;;

let readFile path = ((head1 (chan path)), (readGraph (chan path) (head2 (chan path))));;

let nbsommet (x,_)=x;;
let graph (_,x)=x;;

let premier (x,_,_)=x;;
let deuxieme (_,x,_)=x;;
let troisieme (_,_,x)=x;;

let rec count = function
	[] -> 0
	| i::reste -> 1 + count reste;;

let pop = function
	(i,j,k)::reste -> (i,j,k)
	| [] -> raise (Fail "pop: the list is empty");;

let rec min = function
	(i,j,k)::[] -> (i,j,k)
	| (i,j,k)::reste -> if k > troisieme (min reste) then min reste else (i,j,k)
	| [] -> raise (Fail "min: the list is empty");;

let rec max = function
	(i,j,k)::[] -> (i,j,k)
	| (i,j,k)::reste -> if k < troisieme (max reste) then max reste else (i,j,k)
	| [] -> raise (Fail "max: the list is empty");;

let rec estDans a b = match b with
	| [] -> false
	| i::reste -> if a == i then true else estDans a reste;;

let addSommet min sommet = if (estDans (premier min) sommet) then 
							deuxieme (min)::sommet
						else premier (min)::sommet;;

let possibilite sommet arete = match sommet with
	| []-> false
	| i::reste -> if ((estDans (premier arete) sommet||estDans (deuxieme arete) sommet) 
		&& (not (estDans (premier arete) sommet && estDans (deuxieme arete) sommet )))
		then true 
		else false;;

let rec listePossible sommet graph resultat = match graph with
	| (i,j,k)::reste -> if (possibilite sommet (i,j,k)) then listePossible sommet reste ((i,j,k)::resultat)
												else  listePossible sommet reste resultat
	| [] -> resultat;;

let rec addArbre sommet graph arbre nombreSommet racine = match sommet with
	| [] -> addArbre (racine::sommet) graph arbre nombreSommet racine
	| i::reste -> if count(sommet) == nombreSommet then arbre
					else addArbre (addSommet (min (listePossible sommet graph [])) sommet) graph (min (listePossible sommet graph [])::arbre) nombreSommet racine;;

let rec reverse liste resultat = match liste with
	| [] -> resultat
	| i::reste -> reverse reste (i::resultat);;


let prim graph nbSommet racine = reverse (addArbre [] graph [] nbSommet racine) [];;

let newRacine arete racine = if (premier arete) == racine then deuxieme arete else premier arete;;

let add (i,j,k) plus = (i,j,(k+plus));;

let rec distMax arbre racine = match arbre with
	| [] -> (0,0,0)
	| i::reste -> if ((premier i) == racine) || ((deuxieme i) == racine) then 
		if ((troisieme i) > (troisieme (distMax reste racine))) then 
			add (distMax reste (newRacine i racine)) (troisieme i)
		else add (distMax reste (newRacine i racine)) (troisieme (distMax reste racine))
	else distMax reste racine;;

let rec diametre graph nbSommet racine = 
	if nbSommet == racine then (0,0,0)
	else 
		if (troisieme (distMax (prim graph nbSommet racine) racine)) > (troisieme (diametre graph nbSommet (racine + 1))) then 
			distMax (prim graph nbSommet racine) racine
		else diametre graph nbSommet (racine + 1);;

let diametreFile fichier = diametre (graph (readFile fichier)) (nbsommet (readFile fichier)) 0 ;;

let diametreMain truc = 
	output_string stdout "rentrer le chemin vers le fichier à evaluer  ";
	flush stdout;
	diametreFile (input_line stdin);;

try diametreMain []  with End_of_file ->
(0,0,0);;

