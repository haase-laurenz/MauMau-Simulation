import time

optimal_playstyles = {
    "Karo 7": "Farbe",
    "Karo 8": "Wert",
    "Karo 9": "Wert",
    "Karo 10": "Wert",
    "Karo 11": "Wert",
    "Karo 12": "Wert",
    "Karo 13": "Wert",
    "Karo 14": "Wert",

    "Herz 7": "Farbe",
    "Herz 8": "Farbe",
    "Herz 9": "Farbe",
    "Herz 10": "Wert",
    "Herz 11": "Wert",
    "Herz 12": "Wert",
    "Herz 13": "Wert",
    "Herz 14": "Wert",

    "Pik 7": "Farbe",
    "Pik 8": "Farbe",
    "Pik 9": "Farbe",
    "Pik 10": "Farbe",
    "Pik 11": "Farbe",
    "Pik 12": "Wert",
    "Pik 13": "Wert",
    "Pik 14": "Wert",

    "Kreuz 7": "Farbe",
    "Kreuz 8": "Farbe",
    "Kreuz 9": "Farbe",
    "Kreuz 10": "Farbe",
    "Kreuz 11": "Farbe",
    "Kreuz 12": "Farbe",
    "Kreuz 13": "Farbe",
    "Kreuz 14": "Wert",
}

normalRating = {
    "Karo 7": "0.112903226",
    "Karo 8": "0.193548385",
    "Karo 9": "0.354838713",
    "Karo 10": "0.532258061",
    "Karo 11": "0.725806449",
    "Karo 12": "0.935483867",
    "Karo 13": "1.161290325",
    "Karo 14": "1.403225803",

    "Herz 7": "0.435483872",
    "Herz 8": "0.451612902",
    "Herz 9": "0.483870972",
    "Herz 10": "0.596774191",
    "Herz 11": "0.790322579",
    "Herz 12": "0.999999997",
    "Herz 13": "1.225806455",
    "Herz 14": "1.467741933",

    "Pik 7": "0.822580648",
    "Pik 8": "0.838709678",
    "Pik 9": "0.870967738",
    "Pik 10": "0.919354838",
    "Pik 11": "0.983870968",
    "Pik 12": "1.129032257",
    "Pik 13": "1.354838705",
    "Pik 14": "1.596774193",

    "Kreuz 7": "1.274193544",
    "Kreuz 8": "1.290322584",
    "Kreuz 9": "1.322580644",
    "Kreuz 10": "1.370967744",
    "Kreuz 11": "1.435483874",
    "Kreuz 12": "1.516129034",
    "Kreuz 13": "1.612903224",
    "Kreuz 14": "1.790322583",
}

farben_val={"Karo":0,"Herz":1,"Pik":2,"Kreuz":3}

def updateOptimalPlay():
    
    
    for card in optimal_playstyles.keys():
        currentplaystyle=optimal_playstyles[card]
        currentscore=0
        otherscore=0
        for enemy in optimal_playstyles.keys():
            if card!=enemy:
                currentscore+=calculate_newOffense(card,enemy)
                if (optimal_playstyles[card]=="Farbe"):
                    optimal_playstyles[card]="Wert"
                else:
                    optimal_playstyles[card]="Farbe"
                otherscore+=calculate_newOffense(card,enemy)
                optimal_playstyles[card]=currentplaystyle
                
        currentscore/=31
        otherscore/=31

        if currentscore>otherscore:
            pass
        else:
            
            print(card+" CHANGED PLAYSTYLE")
            time.sleep(10)
        
    
def calculate_defense_score(card1, card2):

    color1, value1 = card1.split()
    color2, value2 = card2.split()


    # Verteidigungsbewertung berechnen
    if optimal_playstyles[card2]=="Farbe":
        if farben_val[color1]>farben_val[color2]:
            return 1
        elif farben_val[color1]==farben_val[color2]:
            return 0.5
        else: 
            return 0
    else:
        if int(value1)>int(value2):
            return 1
        elif int(value1)==int(value2):
            return 0.5
        else:
            return 0
        
def calculate_newOffense(card1, card2):

    color1, value1 = card1.split()
    color2, value2 = card2.split()



    if optimal_playstyles[card1]=="Farbe":
        if farben_val[color1]>farben_val[color2]:
            return float(normalRating[card2])
        elif farben_val[color1]==farben_val[color2]:
            return (float(normalRating[card2]))/2
        else: 
            return 0
    else:
        if int(value1)>int(value2):
            return float(normalRating[card2])
        elif int(value1)==int(value2):
            return (float(normalRating[card2]))/2
        else:
            return 0
        
def calculate_newDefense(card1, card2):

    color1, value1 = card1.split()
    color2, value2 = card2.split()



    if optimal_playstyles[card2]=="Farbe":
        if farben_val[color1]>farben_val[color2]:
            return float(normalRating[card2])
        elif farben_val[color1]==farben_val[color2]:
            return (float(normalRating[card2]))/2
        else: 
            return 0
    else:
        if int(value1)>int(value2):
            return float(normalRating[card2])
        elif int(value1)==int(value2):
            return (float(normalRating[card2]))/2
        else:
            return 0

# Beispielaufruf

for i in range(100):

    newWerte={}
    mittelwert=0
    for Offense in optimal_playstyles.keys():
        new_defenseScore=0
        new_offenseScore=0
        for Defense in optimal_playstyles.keys():
            if Defense!=Offense:
                new_defenseScore += calculate_newDefense(Offense, Defense)
                new_offenseScore += calculate_newOffense(Offense, Defense)
        
        #cleprint(Offense)
        #print(f"Offense: {round(new_offenseScore/31,8)}")
        #print(f"Defense: {round(new_defenseScore/31,8)}")
        
        newWerte[Offense]=(new_offenseScore/31+new_defenseScore/31)/2
    
    print("")
    print("Werte "+str(i+1)+". Gen") 
    print("") 
    for wert in newWerte.keys():
        mittelwert+=newWerte[wert]
        print(newWerte[wert])
    mittelwert/=32
        
    print("")
    print("Werte "+str(i+1)+". Gen genormt - Mittelwert: "+str(mittelwert)) 
    print("") 
    
    for wert in newWerte.keys():
        print(newWerte[wert]/mittelwert)
    
    for karte in normalRating.keys():
        normalRating[karte]=newWerte[karte]/mittelwert
    
    updateOptimalPlay()
        
    print("") 
    print("") 
    print("---------------------------------") 
    print("") 
    print("") 
        