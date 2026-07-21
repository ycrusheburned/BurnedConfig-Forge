import json
import os
import urllib.request

with open("tail.log", "r", errors="replace") as f:
    body = f.read()

data = json.dumps({
    "title": "Build failure log",
    "body": "```\n" + body[-60000:] + "\n```",
}).encode()

req = urllib.request.Request(
    "https://api.github.com/repos/" + os.environ["REPO"] + "/issues",
    data=data,
    headers={
        "Authorization": "token " + os.environ["GH_TOKEN"],
        "Accept": "application/vnd.github+json",
        "Content-Type": "application/json",
    },
    method="POST",
)
urllib.request.urlopen(req)
