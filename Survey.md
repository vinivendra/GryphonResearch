# Survey

The Gryphon Survey was conducted through Google Forms. It was structured as follows.

## Survey Contents

**Title: Research on code maintainability**

*Hello! Thanks for taking your time to help.*

*Please don't answer this form twice! It's anonymous, so we wouldn't be able to delete duplicate answers.*

*First, let's start with the basics: how many years of experience in programming do you have?*

---

**Prompt**: *I have been a programmer for at least... (in years)*

**Answer**: An integer number from 0 to 10.

*And how well would do you know these programming languages?*

---

**Prompt**: *I have used this language for at least... (in years)*

**Questions**: *Java*, *Objective-C*, *Kotlin*, and *Swift*.

**Answers**: One integer number from 0 to 10 for each language.

---

**Next page**

*Now let's get to it.*

*Below are links to four short implementations of the same algorithm. We would like you to look at each one for about a minute or two, then help us figure out which (if any) of them would be hard for a developer to maintain.*

*Note: to make the survey quicker, only a few parts of each implementation are linked below. However, the full implementations will also be linked at the top of each file if you want to see them. You can trust that each of the full implementations works correctly and that they all produce equivalent results.*

---

**Prompt**: *"This implementation would be hard for a developer to maintain."*

**Questions**: *bit.ly/Swift1Snippet*, *bit.ly/Swift2Snippet*, *bit.ly/Kotlin1Snippet*, *bit.ly/Kotlin2Snippet*.

**Answers**: One of "*Strongly disagree*", "*Disagree*", "*Neutral*", "*Agree*", or "*Strongly agree*" for each link.

---

**Prompt**: *Feedback (optional). If you would like to leave any written feedback, feel free to do so below.*

**Answer**: A long text form.

## Survey results

| Programming time | Java | Objective-C | Kotlin | Swift | Swift 1 link | Swift 2 link | Kotlin 1 link | Kotlin 2 link |
| ---:|---:| ---:| ---:| ---:| ---:| ---:| ---:| ---:|
7 | 0 | 1 | 0 | 4 | Disagree | Disagree | Disagree | Disagree
8 | 4 | 7 | 0 | 5 | Strongly disagree | Disagree | Disagree | Strongly disagree
4 | 0 | 0 | 0 | 2 | Agree | Disagree | Neutral | Neutral
4 | 0 | 4 | 0 | 4 | Agree | Strongly agree | Neutral | Neutral
10 | 8 | 8 | 0 | 2 | Strongly disagree | Strongly disagree | Disagree | Disagree
4 | 0 | 1 | 0 | 3 | Neutral | Neutral | Neutral | Neutral
2 | 2 | 0 | 2 | 0 | Disagree | Disagree | Disagree | Strongly disagree
10 | 7 | 0 | 3 | 0 | Agree | Agree | Agree | Agree
10 | 8 | 0 | 3 | 0 | Strongly agree | Strongly agree | Strongly agree | Strongly agree
9 | 0 | 0 | 1 | 0 | Neutral | Neutral | Neutral | Neutral
10 | 8 | 8 | 0 | 5 | Neutral | Neutral | Agree | Agree
10 | 8 | 0 | 2 | 0 | Agree | Agree | Agree | Agree
1 | 1 | 0 | 1 | 1 | Neutral | Neutral | Neutral | Neutral
7 | 0 | 3 | 0 | 2 | Strongly agree | Strongly agree | Strongly agree | Strongly agree
10 | 8 | 8 | 1 | 2 | Disagree | Strongly agree | Neutral | Neutral

Only the 9th respondent left any feedback:

*The differences in the fragments are minimal. They are all architecturally poor. They mix input handling with semantics. There is lots of special casing. Especially the null handling is not needed for a language like Kotlin.*
