#!/usr/bin/env python3
import requests
from bs4 import BeautifulSoup
randomWebsiteProvider='https://techwiser.com/random-useful-websites/'
#Creating Object for exact duplicates of links
class RankedLink:
    def __init__(self,link,count):
        self.link = link
        self.count= count
    def getcount(self):
        return self.count
    def getLink(self):
        return self.link
def getLowerLinks(site):
    try:
        r = requests.get(site)
        soup = BeautifulSoup(r.text, 'html.parser')
        randomLinks = soup.find_all('a')
        return randomLinks
    except:
        print("I found no link here :(");
        pass
def genLinks():
    r = requests.get(randomWebsiteProvider)
    soup = BeautifulSoup(r.text, 'html.parser')
    randomLinks = soup.find_all('a')
    flinks =["http://dunemask.mynetgear.com/"]
    for link in randomLinks:
            print(link)
            try:
                lowerLinks = getLowerLinks(link['href'])
                #print(lowerLinks)
                if None != lowerLinks:
                    for llink in lowerLinks:
                        curLink = llink.get('href')
                        if not(curLink==None):
                            flinks.append(curLink)
            except:
                pass
    return flinks
#Use Merge sort to sort them
def sortLinks(adict):
    rl= []*len(adict)
    for key in adict.keys():
        rl.append([RankedLink(str(key),adict[key])])
    #Format Dictionary into Object Array
    while len(rl)>1:
        # 0 2 3 4....
        for i in range(0,len(rl),2):
            #print("Round:"+str(i))
            first = rl[i]
            if i+1 >= len(rl):
                second = [RankedLink("None",0)] #Add Filler Element
            else:
                second = rl[i+1]
            out = []
            while len(second)>0 and len(first)>0:
              if second[0].count < first[0].count:
                out.append(second.pop(0))
              else:
                out.append(first.pop(0))
            if not(second==None) and len(second)>0:
              while len(second)>0:
                out.append(second.pop(0))
            else:
              while len(first)>0:
                out.append(first.pop(0))
            rl[i]=out
        rl = [x for x in rl if x != []] #Remove empty lists

    rl = rl[0] #Remove From super List, currently [[Value,Value2]]
    for i in range(0,len(rl)): #Remove links with 0 hits (Also removes filler elements)
        if rl[i].count==0:
            rl[i]=[]
    rl = [x for x in rl if x != []] #Remove any leftover empty lists we no longer need
    return rl
#The following code block is just to generate some test links, and filter the "invalid" links out
#START OF CODE SECTION A
links = genLinks()
print("Test Links generated!")
linkDict = {}
#Simplify URL
for link in links:
    originalLink = link
    #Trimming Link for comparison
    if ('.org') in link:
        link = link[:link.index('.org')+4]
    elif ('.com') in link:
        link = link[:link.index('.com')+4]
    elif ('.net') in link:
        link = link[:link.index('.net')+4]
    elif ('.edu') in link:
        link = link[:link.index('.edu')+4]
    elif ('.info') in link:
        link = link[:link.index('.info')+5]
    elif ('.io') in link:
        link = link[:link.index('.io')+3]
    elif ('.') in link:
        link = link[:link.index('.')]
    else:
        print('Unqualified Link!')
        link = None
    if (not(link==None)) and link in linkDict:
        linkDict[link]=linkDict[link]+1
    elif (not(None==link)):
        linkDict[link]=0
#END OF CODE SECTION A
sortedLinks = sortLinks(linkDict)
print("RETURNED SORTED")
#Remove non HTTP(S) links that could be left
finalLinks=[]*len(sortedLinks)
for l in sortedLinks:
    if "http" in l.link:
        finalLinks.insert(0,l) #Insert at front to reverse order
finalLinks = [x for x in finalLinks if x != []]
for l in finalLinks:
    print("Count:"+str(l.count)+", Link:"+str(l.link))
print("DONE!")
