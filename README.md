# simpleEditor
simpleEditor

![image](https://github.com/user-attachments/assets/aa02ddce-bdd4-458f-957c-6448fa7f4159)

# How to use
manual

#base
{

ctrl+v default;

ctrl+c default;

ctrl+x default;

ctrl+s default;

ctrl+o open doc;

ctrl+p print doc;

};

ctrl+w comment;

shift+f format;

#specific
{

ctrl+j notVisible/Visible panel SearchJ - search from number line;

ctrl+f notVisible/Visible panel SearchL - search text in line then use ctrl+down and if end with searching ctrl+q for free container;

ctrl+down;

ctrl+q;

};


#panels
{

alt+v notVisible/Visible viewerFile;

alt+m notVisible/Visible minicli;

alt+q "withFocus-caret on File-space" jump to minicli with save position-caret and "withFocus-caret on minicli-space" return to caretsavepos to File-Space;

alt+up/down/left/right "withFocus-caret on File-space" up/down minicli, "withFocus-caret on File-space" left/right viewerFile;

};
