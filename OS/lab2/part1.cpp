#include <unistd.h>

#include <filesystem>
#include <fstream>
#include <iostream>
#include <string>
#include <vector>

namespace fs = std::filesystem;

using namespace std;

vector<fs::directory_entry> getDirEntry(string dir) {
  vector<fs::directory_entry> entry_dirs;

  for (const auto& entry : fs::directory_iterator(dir)) {
    entry_dirs.push_back(entry);
  }

  return entry_dirs;
}

int isSubstring(const std::string& s1, const std::string& s2)
{

  if (s2.find(s1) != string::npos)
    return (int) s2.find(s1);
  return -1;
}

void search(string path, string line) {
  
  ifstream file(path);
  if (file.is_open()) {
    string file_as_line;
    string buf;
    while (getline(file, buf)) {
      file_as_line += buf;
    }

    int bytes = isSubstring(line, file_as_line);
    if (bytes > 0) {
      cout << "line founded, viewed bytes: ";
      cout << bytes << endl;   
    }
    else {
      cout << "line not founded, viewed bytes: "; 
      cout << file_as_line.size() << endl;
    }
  }
  else {
    cout << "file not founded.";
  }
 
}

int main(int argc, char* argv[]) {
  string path;
  string line;

  if (argv[1] == nullptr) {
    cout << "передайте путь к директории" << endl;
    return 1;
  }
  else {
    path=argv[1];
  }

  if (argv[2] == nullptr) {
    cout << "передайте искомую строку" << endl;
    return 1;
  }
  else {
    line=argv[2];
  }

  vector<fs::directory_entry> file_list = getDirEntry(path);

  cout << "search: \"" << line << "\" in dir:" << path<< endl;

  for (const auto& entry : file_list) {
    if (!entry.is_directory()) {
      pid_t pid = fork();
      if (pid == 0) {
        cout << "pid: " << getpid() << ", ";
        cout << "filename: " << entry.path().filename() << ", ";
        search(entry.path(), line);
        return 0;
      }
    }
  }
  return 0;
}
