import os, errno, sys, shutil

def preProcessing():
	#opening and making of the files
	with open('data/all.txt', 'r') as fileRead:
		data=fileRead.readlines()
	fileWrite = open("LCMinput.txt", "w+")
	fileOutput = open("LCMoutput.txt", "w+")
	fileOutput.write("This file as to be set as the output of SPMF")
	fileOutput.close()
	if not os.path.isdir("tmp"):
	   os.makedirs("tmp")
	cardsIndex = open("tmp/mapCard.txt", "w+")
	cardlist = cardsIndex.readlines() # Si des cartes sont deja stockees dans cardsIndex

	def processPlayer(player, data):
		curr_game = []
		count = -1
		for line in data :
			curr = line.split()[1][:1]
			if player == curr :
				curr_card = line.split()[1][1:]
				if curr_card not in cardlist :
					# On ajoute la carte aux cartes connues
					cardsIndex.write(curr_card + "\n")
					cardlist.append(curr_card)
				# On cherche le numero correspondant a la carte et on l'ecrit dans LCMvalue
				curr_game.append(cardlist.index(curr_card) + 1)
			elif curr == "B":
				count += 1
				if curr_game != []:
					curr_game.sort()
					fileWrite.write(" ".join(str(card) for card in curr_game)+" \n")
					curr_game=[]

		# On ecrit la derniere partie
		if curr_game != []:
			curr_game.sort()
			fileWrite.write(" ".join(str(card) for card in curr_game)+" \n")

	first = True
	processPlayer("M", data)
	processPlayer("O", data)

	fileRead.close()
	fileWrite.close()
	cardsIndex.close()
	exit(0)

def postProcessing():
	#opening of the files
	cardsIndex = open('tmp/mapCard.txt', 'r')
	fileToParse = open("LCMoutput.txt", "r")	
	fileOutput = open("Results.txt", "w+")
	
	def getStringOfCards(line, cardlist):
		res= ""
		cards = line.split("#SUP: ")[0].split()
		for i in cards:
			res = res + cardlist[int(i)-1].split()[0] + ", "
		return res[:-2]
			
	
	cardlist = cardsIndex.readlines()
	lcmResult = fileToParse.readlines()
	for line in lcmResult:
		stri = "Used "+ line.split("#SUP: ")[1].split()[0] +" times: \n{"+ getStringOfCards(line, cardlist)+ "}\n\n"

		fileOutput.write(stri)
	
	cardsIndex.close()
	fileToParse.close()
	fileOutput.close()

	shutil.rmtree("tmp")
	os.remove("LCMinput.txt")
	os.remove("LCMoutput.txt")
	
	exit(0)
	
#_________________________________________MAIN___________________________________________
if(len(sys.argv)<2):
	# must choose before or after spmf processing
	print("Arguments must be choose: \"pre\" or \"post\"")
	exit(0)

if sys.argv[1] == "pre":
	preProcessing()
elif sys.argv[1] == "post":
	postProcessing()
else:
	print("Arguments must be choose: \"pre\" or \"post\"")
	exit(0)

	
