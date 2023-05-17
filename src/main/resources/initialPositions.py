# This board initialises the chess board
# This board can be changed
import rdflib
from rdflib import URIRef, BNode, Literal, Namespace, Graph
from rdflib.namespace import RDF

g = rdflib.Graph()

ns = Namespace("http://example.org/chess/")

# eg
# :queen_w :isIn :d1.
# :d1 :contains :queen_w.


cols = ["a", "b", "c", "d", "e", "f", "g", "h"]


for i in range(16):
    current_piece = URIRef(ns["P"+str(i+1)])
    match i:
        case 0: # king
            current_position = URIRef(ns["e1"])
        case 1: # queen
            current_position = URIRef(ns["d1"])
        case 2: # 1st bishop
            current_position = URIRef(ns["c1"])
        case 3: # 2nd bishop
            current_position = URIRef(ns["f1"])
        case 4: # 1st knight
            current_position = URIRef(ns["b1"])
        case 5: # 2nd knight
            current_position = URIRef(ns["g1"])
        case 6: # 1st rook
            current_position = URIRef(ns["a1"])
        case 7: # 2nd rook
            current_position = URIRef(ns["h1"])
        case _:
            current_position = URIRef(ns[cols[i-8] + "2"])

    g.add((current_piece, ns.isIn, current_position))
    g.add((current_position, ns.contains, current_piece))

print(g.serialize(format="turtle"))
g.serialize(destination="initialPositions.ttl")