readme_file = open('/Users/bnyte/workspaces/tome/backend/study/note/01_python/05_输入与输出/02_读写文件/readme.md', 'r+', encoding='utf-8')
with (readme_file):
    read_data = readme_file.readline()
    print(read_data)

# 我们可以检查该文件是否已自动关闭。
print(readme_file.closed)