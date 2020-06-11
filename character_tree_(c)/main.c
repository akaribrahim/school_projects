#include <stdio.h>
#include <stdlib.h>
#include <string.h>


struct Node{
	struct Node* child[26];
	char password[10];
};typedef struct Node Node;

Node* root;

Node* newNode();
void readInput(FILE* fp,FILE* outputFile);
int getCharIndex(char letter);
void insert(FILE* outputFile,char* name,char* password);
void search_Login_Delete(FILE* outputFile,char* questionedName,char* questionedPass,int whichCommand);
void deleteUser(Node* iterator,char* questionedName,int idx,int anotherChild);
int isThereAnotherChildren(Node* iterator);
void printTheTrie(FILE* outputFile,Node* iterator,char* string,int idx);
void freeTheTrie(Node* iterator);
int main(int argc,char** argv){
	root = newNode();
	FILE* inputFP = fopen(argv[1],"r");
	FILE* outputFP = fopen("myoutput.txt","w");
	readInput(inputFP,outputFP);
	fclose(inputFP);
	freeTheTrie(root);
	return 0;
}

void readInput(FILE* fp,FILE* outputFile){
	char str[25],strForPass[10];
	char* string = (char*)malloc(sizeof(char)*25);
	int counter=1;
	while(!feof(fp)){
		fscanf(fp,"%s",str);
		if(!strcmp(str,"-a")){
			fscanf(fp,"%s",str);
			fscanf(fp,"%s",strForPass);
			insert(outputFile,str,strForPass);
		}
		else if(!strcmp(str,"-s")){
			fscanf(fp,"%s",str);
			search_Login_Delete(outputFile,str,"e",0);
		}
		else if(!strcmp(str,"-q")){
			fscanf(fp,"%s",str);
			fscanf(fp,"%s",strForPass);
			search_Login_Delete(outputFile,str,strForPass,1);
		}
		else if(!strcmp(str,"-d")){
			fscanf(fp,"%s",str);
			search_Login_Delete(outputFile,str,"e",2);
		}
		else if(!strcmp(str,"-l")){
			printTheTrie(outputFile,root,string,0);
		}
		else{
			fprintf(outputFile,"Incorrect Command");
		}
	}
	
	free(string);
	
}
Node* newNode(){
	Node* temp = (Node*)malloc(sizeof(Node));
	temp -> password[0] = 'e'; //'e'(empty) shows that there is no password in this Node
	int i;
	for(i=0;i<26;i++){
		temp -> child[i] = NULL;
	}
	return temp;
}
int getCharIndex(char letter){
	return letter - 'a';
}
char getCharFromIndex(int index){
	return index + 'a';
}
void insert(FILE* outputFile,char* name,char* password){
	Node* iterator = root;
	int i,charIndex;
	for(i=0;name[i];i++){
		charIndex = getCharIndex(name[i]);
		if(iterator -> child[charIndex]==NULL){
			iterator -> child[charIndex] = newNode();
		}
		iterator = iterator -> child[charIndex];
	}
	/*O an ki iteratorda şifre yoksa yani eklenen isim önceden yoksa*/
	if(iterator->password[0] == 'e'){
		strcpy(iterator -> password , password);
		fprintf(outputFile,"\"%s\" was added\n",name);
	}
	else	
		fprintf(outputFile,"\"%s\" reserved username\n",name);
	
}
void search_Login_Delete(FILE* outputFile,char* questionedName,char* questionedPass,int whichCommand){
	//whichCommand 0'sa fonksiyon -s komutundan çağrılmıştır.
	//whichCommand 1'se fonksiyon -q komutundan çağrılmıştır.
	//whichCommand 2 ise fonksiyon -d komutundan çağrılmıştır.
	//<incorrect username>,<no record>,<not enough username> durumları hepsinde aynı.
	
	//eğer aradığımız ismin ilk harfi rootun çocuklarında yoksa kayıt yok de çık
	if(root -> child[getCharIndex(questionedName[0])] == NULL){
		fprintf(outputFile,"\"%s\" no record\n",questionedName);
		return;
	}
	
	int i,charIndex,control=0;
	Node* iterator = root;
	//aradığımız isme harf harf bak eğer aradığımız harfler bulunuyorsa
	//control birer birer artacak 
	for(i=0;questionedName[i];i++){
		charIndex = getCharIndex(questionedName[i]);
		if(iterator -> child[charIndex] != NULL){
			control++;
			iterator = iterator -> child[charIndex];
		}
	}
	//Döngü bittiğinde control ile i eşitse girilen ismin hepsi ağaçta var demektir
	//Eğer bu girilen ismin ağaçta bittiği yerde bir şifre varsa:
	//		Fonksiyon -s komutundan çağrıldıysa şifreyi ve ismi yazdır
	//		Fonksiyon -q komutundan çağrıldıysa:
	//				Ağaçtaki şifre ile inputta girilen şifreyi karşılaştır.
	//		Fonksiyon -d komutundan çağrıldıysa:
	//				
	//Eğer ismin ağaçta bittiği yerde bi şifre yoksa yeterli karakter girilmemiştir.
	if(control == i){
		if(iterator -> password[0] != 'e'){
			if(whichCommand == 0)	
				fprintf(outputFile,"\"%s\" password \"%s\"\n",questionedName,iterator->password);
			else if(whichCommand == 1){
				if(!strcmp(iterator -> password,questionedPass))
					fprintf(outputFile,"\"%s\" successful login\n",questionedName);
				else
					fprintf(outputFile,"\"%s\" incorrect password\n",questionedName);
			}
			else if(whichCommand==2){
				//-d komutundan buraya geldiğimizde input dosyasında verilen ismin triede olduğunu
				//ve bu isme karşılık bi şifre olduğunu biliyoruz.
				//Ama triede geri gidemeyeceğimizden baştan tekrar gidicez.
				fprintf(outputFile,"Bunu yapamadım\n");
				//deleteUser(root -> child[getCharIndex(questionedName[0])],questionedName,1,0);
			}
		}	
		else
			fprintf(outputFile,"\"%s\" not enough username\n",questionedName);
		return;
	}
	//Döngü bittiğinde control 0 dan farklıysa ve i ye eşit değilse bu
	//girilen ismin başlangıcından itibaren bi yere kadar ağaçta olduğunu gösterir
	else if(control != 0){
		if(control<i)	
			fprintf(outputFile,"\"%s\" incorrect username\n",questionedName);
		return;
	}
	
}
int isThereAnotherChildren(Node* iterator){
	int i=0,anotherChild=0;
	for(i;i<26;i++){
		if(iterator -> child[i] != NULL)
			anotherChild++;
	}
	if(anotherChild >= 2)	return 1;
	else	return 0;
}
void deleteUser(Node* iterator,char* questionedName,int idx,int anotherChild){
		int charIndex;
	//Trie içinde böyle bi isim olduğunu bildiğim için kontrole gerek yok.
	if(!questionedName[idx]){
		printf("buda\n");
		//free(iterator);
		return;
	}
	 charIndex = getCharIndex(questionedName[idx++]);
	printf("%c ------ %d\n",getCharFromIndex(charIndex),isThereAnotherChildren(iterator));
	
	deleteUser(iterator -> child[charIndex],questionedName,idx,anotherChild);
	printf("%c ------ %d\n",getCharFromIndex(charIndex),isThereAnotherChildren(iterator));
	/*if(!isThereAnotherChildren(iterator)){
		
		
		free(iterator);
	}*/

}
void printTheTrie(FILE* outputFile,Node* iterator,char* string,int idx){
	if(iterator -> password[0] != 'e'){
		string[idx] = '\0';
		fprintf(outputFile,"%s\n",string);
	} 
	
	int i;
	for(i=0;i<26;i++){
		if(iterator -> child[i] != NULL){
			string[idx] = getCharFromIndex(i);
			printTheTrie(outputFile,iterator -> child[i],string,idx+1);
		}
	}
}
void freeTheTrie(Node* iterator){
	int i;
	
	for(i=0;i<26;i++){
		if(iterator -> child[i]!=NULL)
			freeTheTrie(iterator -> child[i]);
	}
	free(iterator);
}

