# Graph initializing half chess board and white chess pieces
# This graph doesn't change
# STATIC
import rdflib
from rdflib import URIRef, Namespace
from rdflib.namespace import RDF

g = rdflib.Graph()

ns = Namespace("http://example.org/chess/")

# declare the predicates
right= ns.right
left = ns.left
above = ns.above
below = ns.below
tr = ns.tr
tl = ns.tl
br = ns.br
bl = ns.bl

# eg position
#
# :a1 a :tile;
#    :right :b1;
#    :above :a2;
#    :tr :b2.
#
# another eg position d2
#
# :d2 a :tile;
#   :right :e2;
#   :left :c2;
#   :above:d3;
#   :below :d1;
#   :tr :e3;
#   :br :e1;
#   :tl :c3;
#   :bl :c1.


# Rows and columns of the chess board
rows = ["1", "2", "3", "4"]
cols = ["a", "b", "c", "d", "e", "f", "g", "h"]


#Loop through the rows and columns to make nodess and edges between them
for row in rows:
    for col in cols:
        node_uri = URIRef(ns[col+row])
        g.add((node_uri, RDF.type, ns.tile))


for row in rows:
    for col in cols:
        current_node = URIRef(ns[col+row])
        # if cols not at letter H, the next node is to its right
        if (cols.index(col) < len(cols)-1):
            next_node = URIRef(ns[cols[cols.index(col)+1]+row])
            g.add((current_node, right, next_node))

            # this is positions that are not in column h

            # if they are also not in row 4, then it has a top right
            if (rows.index(row) < len(rows)-1):
                # the top right night is cols+1 and row+1
                diag_node = URIRef(ns[cols[cols.index(col)+1]+rows[rows.index(row)+1]])
                g.add((current_node, tr, diag_node))

            # if they are also not in row 1, then it has a bottom right
            if (rows.index(row) > 0):
                diag_node = URIRef(ns[cols[cols.index(col)+1]+rows[rows.index(row)-1]])
                g.add((current_node, br, diag_node))

        # if cols not at letter A, the previous node is to the left
        if (cols.index(col) > 0):
            prev_node = URIRef(ns[cols[cols.index(col)-1]+row])
            g.add((current_node, left, prev_node))

            # these are the positions that aren't in column a

            # if they are also not in row 4, then they have a top left
            if (rows.index(row) < len(rows)-1):
                diag_node = URIRef(ns[cols[cols.index(col)-1]+rows[rows.index(row)+1]])
                g.add((current_node, tl, diag_node))

            # if they are also not in row 1, then they have a bottom left
            if (rows.index(row) > 0):
                diag_node = URIRef(ns[cols[cols.index(col)-1]+rows[rows.index(row)-1]])
                g.add((current_node, bl, diag_node))

        # if rows (num) not 4, then the node above it is above it
        if (rows.index(row) < len(rows)-1):
            above_node = URIRef(ns[col+ rows[rows.index(row)+1]])
            g.add((current_node, above, above_node))

        # if rows != 1 then the node under it is under it
        if (rows.index(row) > 0):
            under_node = URIRef(ns[col+ rows[rows.index(row)-1]])
            g.add((current_node, below, under_node))


        # if node is not in row 4 & is not in column h, it has a top right
# if node is not in row 4 & is not in column a, it has a top left
# if node is not in row 1 & is not in column h, it has a bottom right
# if node is not in row 1 & is not in column a, it has a bottom left


###############################################################################################
# Initialise pieces


pieces = []

# eg piece
#
# :P13 a :Pawn;
#   a :ChessPiece;
#   :colour :white.

for i in range(16):

    current_piece = URIRef(ns["P" + str(i+1)])
    pieces.append(current_piece)
    # define the type of piece
    match i:
        case 0:
            g.add((current_piece, RDF.type, ns.King))

        case 1:
            g.add((current_piece, RDF.type, ns.Queen))

        case 2 | 3:
            g.add((current_piece, RDF.type, ns.Bishop))

        case 4 | 5:
            g.add((current_piece, RDF.type, ns.Knight))

        case 6 | 7:
            g.add((current_piece, RDF.type, ns.Rook))
        case _:
            g.add((current_piece, RDF.type, ns.Pawn))

    g.add((current_piece, RDF.type, ns.ChessPiece))
    g.add((current_piece, ns.colour, ns.white))





print(g.serialize(format="turtle"))
g.serialize(destination="halfChessBoard.ttl")