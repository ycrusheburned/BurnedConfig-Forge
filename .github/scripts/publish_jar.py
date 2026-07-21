import base64
import glob
import json
import os
import urllib.request

paths = glob.glob("forge/build/libs/BurnedConfig-forge-*-1.20.1.jar")
if not paths:
    raise SystemExit("No jar found to publish")

with open(paths[0], "rb") as f:
    b64 = base64.b64encode(f.read()).decode()

data = json.dumps({
    "title": "Built jar (base64): " + os.path.basename(paths[0]),
    "body": "```\n" + b64 + "\n```",
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
