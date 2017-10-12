db.player.aggregate({"$match" : {"free_agent" : true}},
{"$unwind" : "$mentions"},
                    {"$project" : {"fullname" : 1, "position" : 1, "_id" : 0, "mlbTeam": 1, "mentions.rank" : 1} },
                    {"$sort" : {rank : 1}}
                    )