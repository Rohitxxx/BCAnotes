package com.dhitiedutech.bcanotes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.dhitiedutech.bcanotes.Classes.RecyclerItemClickListener;
import com.dhitiedutech.bcanotes.Modals.CourseDetails;
import com.dhitiedutech.bcanotes.Adapters.PdfListAdapter;
import com.dhitiedutech.bcanotes.Modals.PdfListModel;
import com.dhitiedutech.bcanotes.R;
import com.dhitiedutech.bcanotes.databinding.ActivityChapterListBinding;

import java.util.ArrayList;

public class ChapterList extends AppCompatActivity {


    ActivityChapterListBinding binding;
    String [][][]chapterlist;
    public ChapterList(){
        chapterlist= new String[][][]{

                {
                        {
                                "Introduction to Computers",
                                "Algorithm and Flowcharts",
                                "Operating System and Services in O.S",
                                "Windows Operating Environment",
                                "Editors and Word Processors",
                                "Spreadsheets and Database packages"
                        },
                        {
                                "Introduction to ‘C’ Language",
                                "Operators",
                                "Control structures",
                                "Introduction to problem solving",
                                "Simple Arithmetic Problems",
                                "Functions"
                        },
                        {
                                "Nature of Management",
                                "Evolution of Management Thought",
                                "Functions of Management: Part-I",
                                "Functions of Management: Part-II",
                                "Management of Change",
                                "Strategic Management"
                        },
                        {
                                "Means of Communication",
                                "Types of Communication",
                                "Written Communication",
                                "Business Letters & Reports",
                                "Drafting of business letters",
                                "Information Technology for Communication"
                        },
                        {
                                "DETERMINANTS",
                                "LIMITS & CONTINUITY",
                                "DIFFERENTIATION",
                                "INTEGRATION",
                                "VECTOR ALGEBRA"
                        }
                },
                {
                        {
                                "Arrays",
                                "Pointers",
                                "Strings",
                                "Structures",
                                "Introduction C Preprocessor",
                                "File Handling"
                        },
                        {
                                "Logic gates & circuit",
                                "Combinational Building Blocks",
                                "Memories",
                                "Sequential Building Blocks",
                                "Memory Organization"
                        },
                        {
                                "Fundamentals of Organizational Behaviour",
                                "Perception, Attitude, Values and Motivation",
                                "Personality",
                                "Work stress",
                                "Group Behaviour & Leadership",
                                "Conflict in Organisations"
                        },
                        {
                                "Overview",
                                "Basics of accounting",
                                "Financial statement analysis",
                                "Financial Management",
                                "Work capital",
                                "Cash Management"
                        },
                        {
                                "Sets",
                                "Relations and functions",
                                "Partial Order Relations and lattices",
                                "Functions of several variables",
                                "3D Coordinate Geometry",
                                "Multiple Integration"
                        }

                },
                {
                        {
                                "Introduction",
                                "Classes and Objects",
                                "Inheritance and Polymorphism",
                                "Generic function",
                                "Files and Exception Handling"
                        },
                        {
                                "Introduction to Data Structure and its Characteristics",
                                "Stacks & Queues",
                                "Lists",
                                "Trees",
                                "B-trees",
                                "Sorting Techniques"
                        },
                        {
                                "Introduction ",
                                "Central Processing Unit",
                                "Computer Arithmetic",
                                "Input-Output Organization",
                                "Evaluation of microprocessor",
                                "Assembly Language"
                        },
                        {
                                "The Scope and Method of Economics",
                                "Market Structure",
                                "Macro Economic Concerns",
                                "World Economy"
                        },
                        {
                                "Population, Sample and Data Condensation",
                                "Measures of Central Tendency",
                                "Measures of Dispersion",
                                "Permutations and Combinations",
                                "Sample space, Events and Probability",
                                "Statistical Quality Control"
                        }

                },
                {
                        {
                                "Introduction",
                                "Hardcopy Technology",
                                "Geometrical Transformation",
                                "Representing Curves & Surfaces",
                                "Introductory Concepts",
                                "Multimedia"
                        },
                        {
                                "Introduction",
                                "Processes,CPU Scheduling,Process Synchronization",
                                "Deadlocks",
                                "Device Management",
                                "Information Management"
                        },
                        {
                                "Software Engineering",
                                "Requirements Analysis",
                                "Designing Software Solutions",
                                "Software Implementation",
                                "Software Maintenance",
                                "Comprehensive Example"
                        },
                        {
                                "Linear programming",
                                "Queuing Theory",
                                "Replacement Theory",
                                "Inventory Theory",
                                "Job Sequencing"
                        },
                        {
                                "Complex Variables",
                                "Sequence series and Convergence",
                                "Vector Calculus",
                                "Fourier Series",
                                "Ordinary Differential Equations of First Order",
                                "Ordinary Differential Equations of Second Order"
                        }
                },
                {
                        {
                                "Introduction",
                                "E-R Modeling",
                                "File Organization",
                                "Relational data model",
                                "EER & ER to relational mapping",
                                "Data Normalization"
                        },
                        {
                                "Java Programming",
                                "Java applets",
                                "Networking",
                                "HTML",
                                "Java Servlets",
                                "Java Server Pages"
                        },
                        {
                                "Introduction",
                                "Transmission Media",
                                "Telephony",
                                "Devices",
                                "Transport and upper layers in OSI Model"
                        },
                        {
                                "Roots of Equations",
                                "Interpolation and Extrapolation",
                                "Numerical Differentiation Numerical Integration",
                                "Solution of Linear Equation",
                                "Solution of Differential Equations"
                        }
                },
                {
                        {
                                "Introduction",
                                "Network Security",
                                "IP Security Architecture",
                                "Web Security",
                                "Network Management Security",
                                "System Security"
                        },
                        {
                                "Overview of System Analysis and Design",
                                "Information Requirement Analysis",
                                "Developing a Proposal",
                                "Application Development Methodologies & CASE tools",
                                "Design and Implementation on OO Platform",
                                "Managerial issues in Software Projects"
                        },
                        {
                                "Introduction to E-Commerce",
                                "Business-to-Business Electronic Commerce",
                                "Internet & Extranet",
                                "Public Policy",
                                "Infrastructure for EC"
                        },
                        {
                                "Business Intelligence & Business Decisions",
                                "Executive Information and support Systems",
                                "Multi-Dimensional analysis",
                                "Knowledge Management Systems"
                        }
                }

        };

        // Log.d("error","constructor working");

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityChapterListBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
//        Intent intent = getIntent();
        //populating chapter list to the list
        if(CourseDetails.SEM>0 && CourseDetails.SUBJECT>0 && CourseDetails.NOTES>0){

            try{
                ArrayList<PdfListModel> list =new ArrayList<>();
                // Setting unit list by subject and semester from static variable set by previous activity to list
                for(int i=0;i<chapterlist[CourseDetails.SEM-1][CourseDetails.SUBJECT-1].length;i++){

                    list.add(new PdfListModel(i+1, R.drawable.notes,chapterlist[CourseDetails.SEM-1][CourseDetails.SUBJECT-1][i]));
                }
                PdfListAdapter adapter = new PdfListAdapter(list,this);
                binding.recyclerView.setAdapter(adapter);
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
                binding.recyclerView.setLayoutManager(linearLayoutManager);
                binding.recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(
                        this, binding.recyclerView, new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
//                        Toast.makeText(ChapterList.this, Integer.valueOf(position).toString(), Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(ChapterList.this,DocumentViewer.class);
                        CourseDetails.UNIT=Integer.valueOf(position)+1;
                        startActivity(intent);
                    }

                    @Override
                    public void onItemLongClick(View view, int position) {

                    }
                }
                ));

            }catch (Exception e){
                Toast.makeText(ChapterList.this, "something went wrong please try again", Toast.LENGTH_SHORT).show();
                Log.d("error",e.getMessage());
            }

        }
        //populating papers to the list
        else
            Toast.makeText(ChapterList.this, "Selection failed", Toast.LENGTH_SHORT).show();

    }
    @Override
    public void onBackPressed(){
        super.onBackPressed();
        //Reseting the variable to navigate correctly
        CourseDetails.SUBJECT=0;
//        Toast.makeText(ChapterList.this, "Back button pressed", Toast.LENGTH_SHORT).show();
    }
}