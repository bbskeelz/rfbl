db.getCollection('resource').aggregate([{$match : {'_id' : '2018-Baseball-America'}},
                                        {$unwind : '$ranks'},
                                        {$project : {'_id' : 0,
                                                     'rank' : '$ranks.mentions.2018-Baseball-America.rank',
                                                     'name' : '$ranks.fullname',
                                                     'pos' : '$ranks.position',
                                                     'mlb' : '$ranks.pro_team',
                                                     'team' : '$ranks.fantasy_team_name',
                                                     'FA' : '$ranks.free_agent'}},
                                        {$sort : {'team':1}}
                                     ])
