#include <iostream>
#include <fstream>
#include <string>
#include <cstring>
#include <sstream>
#include <cstdlib>
#include <vector>
using namespace std;

struct SubNode{
	string awayTeam;
	int minuteOfGoal;
	int matchID;
	SubNode* next;
	SubNode* back;
};typedef struct SubNode SubNode;


struct BaseNode{
	string footballerName;
	string teamName;
	SubNode* subHead;
	BaseNode* next;
};typedef struct BaseNode BaseNode;

BaseNode* baseHead;

void insertToSubNode(string footballerName,string teamName,string awayTeam,int minuteOfGoal,int matchID);
void insertToBaseNode(string footballerName,string teamName,string awayTeam,int minuteOfGoal,int matchID);
void printAllList();
void printOut1to5(ofstream& outputFile);
void printAscendingOrder(ofstream& outputFile,string footballerName,SubNode* iteratorSub);
void printDescendingOrder(ofstream& outputFile,string footballerName,SubNode* iteratorSub,int counter);
void doOperations(ofstream& outputFile,string footballerName,int operationNumber,int counter);
int main(int argc,char** argv){
	string line,footballerName,teamName,awayTeam,goalMin,mID;
	string first,second;
	int minuteOfGoal,matchID,operationNumber=1;
	//Firstly,we open inputFile and read it line by line.Then,we split it and send these informations
	//to insertToBaseNode function to create linked list.
	ifstream inputFile(argv[1]);	
	while(getline(inputFile,line)){
		stringstream ss(line);
		getline(ss,footballerName,',');
		getline(ss,teamName,',');
		getline(ss,awayTeam,',');
		getline(ss,goalMin,',');
		getline(ss,mID,',');
		minuteOfGoal=atoi(goalMin.c_str());
		matchID = atoi(mID.c_str());
		insertToBaseNode(footballerName,teamName,awayTeam,minuteOfGoal,matchID);
	}
	inputFile.close();
	
	//printAllList();
	ofstream outputFile(argv[3]);
	printOut1to5(outputFile);
	ifstream operationsFile(argv[2]);
	while(getline(operationsFile,line)){
		stringstream ss(line);
		getline(ss,first,',');
		getline(ss,second,',');
		if(operationNumber==1)
			outputFile<<"6)MATCHES OF GIVEN FOOTBALLER"<<endl;
		else if(operationNumber==2) 
			outputFile<<"7)ASCENDING ORDER ACCORDING TO MATCH ID"<<endl;
		else if(operationNumber==3)
			outputFile<<"8)DESCENDING ORDER ACCORDING TO MATCH ID"<<endl;
		doOperations(outputFile,first,operationNumber,1);
		doOperations(outputFile,second,operationNumber,2);
		operationNumber++;
	}
	operationsFile.close();
	outputFile.close();
	
	return 0;
}
void insertToSubNode(BaseNode* iterator,string footballerName,string teamName,string awayTeam,int minuteOfGoal,int matchID){
	
	//
	SubNode* temp = new SubNode();
	temp -> awayTeam = awayTeam;
	temp -> minuteOfGoal = minuteOfGoal;
	temp -> matchID = matchID;
	
	if(iterator->subHead==NULL){
		temp -> back = NULL;
		temp -> next = NULL;
		iterator -> subHead = temp;
		return;
	}
	SubNode* iteratorSub = iterator->subHead;
	SubNode* iteratorSubBack=NULL;
	while(iteratorSub != NULL){
		if(iteratorSub->matchID > matchID){
			if(iteratorSub == iterator->subHead){
				iterator -> subHead = temp;
				temp -> next = iteratorSub;
				temp -> back = NULL;
				iteratorSub -> back = temp;
				return;
			}
			else{
				iteratorSubBack -> next = temp;
				temp -> next = iteratorSub;
				temp -> back = iteratorSubBack;
				iteratorSub -> back = temp;
				return;
			}
		}
		if(iteratorSub==iterator->subHead) iteratorSubBack=iteratorSub;
		else	iteratorSubBack=iteratorSubBack->next;
		iteratorSub = iteratorSub -> next;
	}
	iteratorSubBack -> next = temp;
	temp -> back = iteratorSubBack;
	temp -> next = NULL; 
	
	
}
void insertToBaseNode(string footballerName,string teamName,string awayTeam,int minuteOfGoal,int matchID){
	//Create an iterator
	BaseNode* iterator = baseHead;
	//We look for a match for the new element
	while(iterator!=NULL){
		if(iterator->footballerName.compare(footballerName)==0){
			//If there is a match then we do not add it to Base List again.
			//Just add a new node to this element.
			insertToSubNode(iterator,footballerName,teamName,awayTeam,minuteOfGoal,matchID);
			return;
		}
		iterator = iterator->next;
	}
	
	//If this footballer is not in the list, we create a new node.
	BaseNode* temp = new BaseNode();
	temp->footballerName = footballerName;
	temp->teamName = teamName;
	temp->next = NULL;
	if(baseHead==NULL){
		//If list is empty, We mark this point as the starting point of the list.
		baseHead=temp;
		insertToSubNode(temp,footballerName,teamName,awayTeam,minuteOfGoal,matchID);
		return;
	}
	else{
		//If we are here, this means that list is not empty and the footballer we read from file is not in the list.
		iterator = baseHead;
		BaseNode* iteratorBack=NULL;
		while(iterator!=NULL){
			if(iterator->footballerName.compare(footballerName)>0){
				//Alfabeye göre gezicinin üstünde olduğu futbolcudan daha önce gelmesi
				//gereken bir futbolcu eklenecek
				if(iterator==baseHead){
					//Eğer gezici listenin başındaysa, listenin ilk elemanı yeni eklenen eleman olmalıdır.
					baseHead = temp;
					temp->next = iterator;
					insertToSubNode(temp,footballerName,teamName,awayTeam,minuteOfGoal,matchID);
					return;
				}
				else{
					iteratorBack->next=temp;
					temp->next=iterator;
					insertToSubNode(temp,footballerName,teamName,awayTeam,minuteOfGoal,matchID);
					return;
				}
			}
			if(iterator==baseHead) iteratorBack=iterator;
			else	iteratorBack=iteratorBack->next;
			iterator = iterator->next;
		}
		
		iteratorBack->next = temp;
		insertToSubNode(temp,footballerName,teamName,awayTeam,minuteOfGoal,matchID);
		return;
	}
	
	
}
void printAllList(){
	BaseNode* iterator = baseHead;
	SubNode* iteratorSub ;
	while(iterator!=NULL){
		cout<<iterator->footballerName<<" "<<iterator->teamName<<endl;
		iteratorSub = iterator->subHead;
		while(iteratorSub != NULL){
			cout<<"\t"<<iteratorSub->awayTeam<<" "<<iteratorSub->minuteOfGoal<<" "<<iteratorSub->matchID<<endl;
			iteratorSub = iteratorSub->next;
		}
		iterator=iterator->next;
	}
}
void printOut1to5(ofstream& outputFile){
	BaseNode* iterator = baseHead;
	SubNode* iteratorSub = iterator->subHead;
	int numberofFootballer=0,goal=0,i=0;
	int firstHalf=0,secondHalf=0,maxGoal=0;
	int isHatTrick=0,isThere=0;
	int* numberofGoals; 
	
	while(iterator!=NULL){
		numberofFootballer++;
		iterator = iterator -> next;
	}
	numberofGoals = (int*)malloc(numberofFootballer*sizeof(int));
	
	iterator = baseHead;
	while(iterator!=NULL){
		goal=0;
		iteratorSub = iterator->subHead;
		while(iteratorSub!=NULL){
			goal++;
			if(iteratorSub->minuteOfGoal <= 45)	firstHalf++;
			else	secondHalf++;
			iteratorSub = iteratorSub -> next;
		}
		if(i==0)	maxGoal = goal;
		else if(goal > maxGoal)	maxGoal = goal;
		numberofGoals[i++] = goal;
		iterator = iterator -> next;
	}
	
	//The period in which the most goals are scored in the league
	//If the first half, print 0. Otherwise, print 1.
	outputFile <<"1)THE MOST SCORED HALF"<<endl;
	if(firstHalf > secondHalf)	outputFile <<0<<endl;
	else if(firstHalf < secondHalf) outputFile <<1<<endl;
	else	outputFile <<-1<<endl;
	
	//Find the top goal scorer and print his name on the screen. 
	outputFile <<"2)GOAL SCORER"<<endl;
	iterator = baseHead;
	i=0;
	while(iterator!=NULL){
		if(maxGoal == numberofGoals[i])
			outputFile <<iterator->footballerName<<endl;
		i++;
		iterator = iterator -> next;
	}
	
	//To print the names of footballers who scored hat-trick.
	outputFile <<"3)THE NAMES OF FOOTBALLERS WHO SCORED HAT-TRICK"<<endl;
	iterator = baseHead;
	i=0;
	while(iterator!=NULL){
		if(numberofGoals[i] >= 3){
			isHatTrick=0;
			iteratorSub = iterator->subHead;
			while(iteratorSub->next != NULL){
				if(iteratorSub->matchID == iteratorSub->next->matchID){
					isHatTrick++;
					if(isHatTrick >= 2){	
						outputFile <<iterator->footballerName<<endl;
						break;
					}
				}		
				else
					isHatTrick=0;
				iteratorSub = iteratorSub -> next;
			}
			
		}
		i++;
		iterator = iterator -> next;
	}
	
	//Print the team list in the league
	outputFile <<"4)LIST OF TEAMS"<<endl;
	vector<string> listofTeams;
	i=0;
	iterator = baseHead;
	while(iterator!=NULL){
		isThere=0;
		if(iterator==baseHead)
			listofTeams.push_back(iterator->teamName);
		else{
			for(i=0;i<listofTeams.size();i++){
				if(iterator->teamName.compare(listofTeams[i])==0){
					isThere=1;
					break;
				}	
			}
			if(!isThere)
				listofTeams.push_back(iterator->teamName);
		}
		iterator = iterator -> next;
	}
	for(i=0;i<listofTeams.size();i++){
		outputFile <<listofTeams[i]<<endl;
	}
	
	
	//Print the list of footballers
	outputFile <<"5)LIST OF FOOTBALLERS"<<endl;
	iterator = baseHead;
	while(iterator!=NULL){
		outputFile <<iterator->footballerName<<endl;
		iterator = iterator -> next;
	}
}
void printAscendingOrder(ofstream& outputFile,string footballerName,SubNode* iteratorSub){
	if(iteratorSub == NULL)	
		return;
	else if(iteratorSub->back==NULL || iteratorSub->matchID != iteratorSub->back->matchID)	
		outputFile<<"footballer Name: "<<footballerName<<",Match ID: "<<iteratorSub->matchID<<endl;
	
	printAscendingOrder(outputFile,footballerName,iteratorSub->next);
}
void printDescendingOrder(ofstream& outputFile,string footballerName,SubNode* iteratorSub,int counter){
	if(iteratorSub == NULL)	
		return;
	
	printDescendingOrder(outputFile,footballerName,iteratorSub->next,counter);
	if(iteratorSub->back==NULL || iteratorSub->matchID != iteratorSub->back->matchID)
		if(iteratorSub->back==NULL && counter == 2)
			outputFile<<"footballer Name: "<<footballerName<<",Match ID: "<<iteratorSub->matchID;
		else
			outputFile<<"footballer Name: "<<footballerName<<",Match ID: "<<iteratorSub->matchID<<endl;
}
void doOperations(ofstream& outputFile,string footballerName,int operationNumber,int counter){
	BaseNode* iterator = baseHead;
	SubNode* iteratorSub = NULL;
	if(operationNumber==1){
		while(iterator != NULL){
			if(iterator -> footballerName.compare(footballerName)==0){
				outputFile<<"Matches of "<<footballerName<<endl;
				iteratorSub = iterator->subHead;
				while(iteratorSub != NULL){
					outputFile<<"Footballer Name: "<<iterator->footballerName<<",Away Team: "<<iteratorSub->awayTeam;
					outputFile<<",Min of Goal: "<<iteratorSub->minuteOfGoal<<",Match ID: "<<iteratorSub->matchID<<endl;
					iteratorSub = iteratorSub->next;
				}
			}
			iterator = iterator -> next;
		}
	}
	else if(operationNumber==2){
		while(iterator != NULL){
			if(iterator -> footballerName.compare(footballerName)==0)
				printAscendingOrder(outputFile,iterator->footballerName, iterator->subHead);
			iterator = iterator -> next;
		}
	}
	else if(operationNumber==3){
		while(iterator != NULL){
			if(iterator -> footballerName.compare(footballerName)==0)
				printDescendingOrder(outputFile,iterator->footballerName, iterator->subHead,counter);
			iterator = iterator -> next;
		}
	}
}