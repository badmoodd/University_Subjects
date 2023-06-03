#include <iostream>
#include <stdio.h>
using namespace std;
struct Node
{
	char name[10];
	Node* left=nullptr;
	Node* right=nullptr;
};
//Assign Node to tree
Node* addNode(Node* root, const char n[]) {
	if (root==nullptr){
		root = new Node;
		strcpy_s(root->name, n);
		cout << root->name;
		return root;
	}
	if (root->left==nullptr) {
		root->left = new Node;
		strcpy_s(root->left->name, n);
		return root;
	}
	if (root->right == nullptr) {
		root->right = new Node;
		strcpy_s(root->right->name, n);
		return root;
	}
	return root;
}
//Clear memory
Node* delTree(Node* root) {
	if (!root) return nullptr;
	delTree(root->left);
	delTree(root->right);
	delete(root);
}
//Show tree
void printTree(Node* root) {
	if (!root) return;
	printTree(root->left);
	cout << root->name<< " ";
	printTree(root->right);
}
int main()
{
	Node* root = nullptr;
	char n[20];
	for (int i = 0; i < 3; i++)
	{
		cout << "Node name : ";
		cin >> n;
		root=addNode(root, n);
	}
	cout << endl;
	printTree(root);
	cout << endl;
	cout << "Droping tree...."<<endl;
	root=delTree(root);
	cout << "Tree has been droped successfuly";
	printTree(root);
}

