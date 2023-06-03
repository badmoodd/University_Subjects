#include <unistd.h>

#include <filesystem>
#include <fstream>
#include <iostream>
#include <sstream>
#include <string>
#include <vector>

namespace fs = std::filesystem;

using namespace std;

void checkInput(int argc, char *argv[], string &dir1, string &dir2,
                unsigned int &MAXFORKSCOUNT) {
  if (string(argv[1]) == string("--help")) {
    cout << "usage: run [/path/to/first/dir] [/path/to/second/dir] "
            "[MAX_FORKS_COUNT]"
         << endl;
    exit(1);
  }

  if (argv[1] == nullptr) {
    cout << "put first dir" << endl;
    exit(1);
  } else {
    dir1 = argv[1];
  }

  if (argv[2] == nullptr) {
    cout << "put second dir" << endl;
    exit(1);
  } else {
    dir2 = argv[2];
  }

  if (argv[3] == nullptr) {
    cout << "put forks max count" << endl;
    exit(1);
  } else {
    stringstream count(argv[3]);
    count >> MAXFORKSCOUNT;
  }
}

vector<fs::directory_entry> getDirEntry(string dir) {
  vector<fs::directory_entry> entry_dirs;

  for (const auto &entry : fs::directory_iterator(dir)) {
    entry_dirs.push_back(entry);
  }
  return entry_dirs;
}

bool compareFiles(const fs::directory_entry &first,
                  const fs::directory_entry &second) {
  std::ifstream f1(first.path(), std::ifstream::binary | std::ifstream::ate);
  std::ifstream f2(second.path(), std::ifstream::binary | std::ifstream::ate);

  if (f1.fail() || f2.fail()) {
    return false;  // file problem
  }

  if (f1.tellg() != f2.tellg()) {
    return false;  // size mismatch
  }

  // seek back to beginning and use std::equal to compare contents
  f1.seekg(0, std::ifstream::beg);
  f2.seekg(0, std::ifstream::beg);
  return std::equal(std::istreambuf_iterator<char>(f1.rdbuf()),
                    std::istreambuf_iterator<char>(),
                    std::istreambuf_iterator<char>(f2.rdbuf()));
}

void executeTask(int FORKSCOUNT, fs::directory_entry entry_dir1,
                 fs::directory_entry entry_dir2) {
  cout << "[pcounts:" << FORKSCOUNT << "]  pid: " << getpid() << ", ";
  cout << "comparing: " << entry_dir1.path().filename() << ", status: "
       << (compareFiles(entry_dir1, entry_dir2) ? "true" : "false") << endl;

  exit(0);
}

int main(int argc, char *argv[]) {
  string dir1;
  string dir2;

  unsigned int MAXFORKSCOUNT;
  unsigned int FORKSCOUNT = 0;

  int status;

  checkInput(argc, argv, dir1, dir2, MAXFORKSCOUNT);


  for (const auto &entry_dir1 : getDirEntry(dir1)) {
    for (const auto &entry_dir2 : getDirEntry(dir2)) {

      if (!entry_dir1.is_directory() && !entry_dir2.is_directory()) {
        if (entry_dir1.path().filename() == entry_dir2.path().filename()) {
          sleep(1);
          FORKSCOUNT++;

          pid_t pid = fork();

          if (pid == 0) {
            executeTask(FORKSCOUNT, entry_dir1, entry_dir2);
          }

          if (FORKSCOUNT >= MAXFORKSCOUNT) {
            // cout << "waiting pid" << endl;
            waitpid(pid, &status, 0);
            FORKSCOUNT--;
          }
        }
      }
    }
  }
  return 0;
}
